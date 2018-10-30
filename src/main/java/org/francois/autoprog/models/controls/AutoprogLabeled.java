package org.francois.autoprog.models.controls;

import org.francois.autoprog.service.sqlite.SqliteServiceInsert;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AutoprogLabeled extends AutoprogControl {
    protected String label;

    public AutoprogLabeled(Class t) {
        super(t);
        label = t.getSimpleName();
        updateControlOnWindow();
    }

    public AutoprogLabeled(ResultSet control, ResultSet position, ResultSet size, ResultSet styles, ResultSet values) throws SQLException, ClassNotFoundException {
        super(control, position, size, styles);

        label = values.getString("value");
        updateControlOnWindow();
    }

    public void setLabel(String label) {
        this.label = label;
        isSaved = false;
        updateControlOnWindow();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void saveValues() throws SQLException {
        SqliteServiceInsert.insertControlsValues(idText, label);
    }
}
