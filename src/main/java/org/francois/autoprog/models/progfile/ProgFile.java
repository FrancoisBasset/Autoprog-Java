package org.francois.autoprog.models.progfile;

import javafx.scene.control.Control;
import jdk.jshell.spi.ExecutionControl;
import org.francois.autoprog.models.controls.AutoprogButton;
import org.francois.autoprog.models.controls.AutoprogControl;
import org.francois.autoprog.models.controls.AutoprogLabel;
import org.francois.autoprog.models.controls.AutoprogTextField;
import org.francois.autoprog.models.exceptions.AlreadyExistingIdControlException;
import org.francois.autoprog.service.sqlite.SqliteService;
import org.francois.autoprog.service.sqlite.SqliteServiceSelect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//todo saveas

public class ProgFile {
    public static ProgFile openedProgFile;
    private Path tmpFile;
    private Path progFile;
    private List<AutoprogControl> controls = new ArrayList<>();
    private boolean saved;

    public ProgFile() throws SQLException, IOException {
        createTemporarySqliteFile();
        createSqliteDatabase();
        saved = false;
    }

    public ProgFile(Path progFile) throws SQLException, ClassNotFoundException {
        this.progFile = progFile;
        SqliteService.connectToDatabase(progFile);

        for (Integer controlId : SqliteServiceSelect.getAllControlsId()) {
            List<ResultSet> sets = SqliteServiceSelect.selectControl(controlId);

            switch (SqliteServiceSelect.getClassOfControl(sets).getSimpleName()) {
                case "Button":
                    controls.add(new AutoprogButton(sets.get(0), sets.get(1), sets.get(2), sets.get(3), sets.get(4)));
                    break;
                case "TextField":
                    controls.add(new AutoprogTextField(sets.get(0), sets.get(1), sets.get(2), sets.get(3), sets.get(4)));
                    break;
                case "Label":
                    controls.add(new AutoprogLabel(sets.get(0), sets.get(1), sets.get(2), sets.get(3), sets.get(4)));
                    break;
            }
        }

        saved = true;
    }

    private void createTemporarySqliteFile() throws IOException {
        tmpFile = Files.createTempFile("newProg", ".prog");
    }

    private void createSqliteDatabase() throws SQLException {
        SqliteService.connectToDatabase(tmpFile);
        SqliteService.createTables();
    }

    public void save(Path path) throws SQLException, IOException, ExecutionControl.NotImplementedException {
        progFile = path;
        save();
    }

    public void saveAs(Path path) throws SQLException, IOException, ExecutionControl.NotImplementedException {
        if (tmpFile == null) {
            tmpFile = progFile;
        }

        save(path);
    }

    public void save() throws IOException, SQLException, ExecutionControl.NotImplementedException {
        for (AutoprogControl control : controls) {
            control.saveNewControlInBase();
        }

        if (tmpFile != null) {
            SqliteService.disconnect();
            Files.move(tmpFile, progFile);
            tmpFile = null;
            SqliteService.connectToDatabase(progFile);
        }

        saved = true;
    }

    public void close() throws SQLException, IOException {
        SqliteService.disconnect();

        if (tmpFile != null && Files.exists(tmpFile)) {
            Files.delete(tmpFile);
        }

        for (AutoprogControl control : controls) {
            control.eraseControlOnWindow();
        }
    }

    public void addControl(AutoprogControl control) throws AlreadyExistingIdControlException {
        for (AutoprogControl c : controls) {
            if (c.getId() == control.getId()) {
                throw new AlreadyExistingIdControlException();
            }
        }

        controls.add(control);
    }

    public void removeControl(AutoprogControl control) {
        controls.remove(control);
    }

    public String getFileName() {
        if (progFile == null) {
            return "Nouveau fichier";
        } else {
            return String.valueOf(progFile.getFileName());
        }
    }

    public boolean isAlreadySaved() {
        return progFile != null;
    }

    public List<AutoprogControl> getControls() {
        return controls;
    }
}