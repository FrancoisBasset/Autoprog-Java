package org.francois.autoprog.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import jdk.jshell.spi.ExecutionControl;
import org.francois.autoprog.helpers.MainHelper;
import org.francois.autoprog.helpers.MenuBarHelper;
import org.francois.autoprog.helpers.StageHelper;
import org.francois.autoprog.models.controls.AutoprogButton;
import org.francois.autoprog.models.controls.AutoprogLabel;
import org.francois.autoprog.models.exceptions.AlreadyExistingIdControlException;
import org.francois.autoprog.models.progfile.ProgFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MenuBarController {
    @FXML
    public MenuItem fullscreenMenuItem;
    @FXML
    public MenuItem editorMenuItem;

    public void initialize() {
        Controllers.menuBar = this;
    }

    @FXML
    public void onNewFileMenuItemAction() {
        try {
            ProgFile.openedProgFile.close();
            ProgFile.openedProgFile = new ProgFile();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        StageHelper.changeTitleWindow();

        AutoprogButton b = new AutoprogButton();
        try {
            b.saveNewControlInBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onOpenFileMenuItemAction() {
        File file = MenuBarHelper.getChooser().showOpenDialog(StageHelper.getPrimaryStage());

        if (file != null) {
            try {
                ProgFile.openedProgFile.close();
                ProgFile.openedProgFile = new ProgFile(file.toPath());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            StageHelper.changeTitleWindow();
        }
    }

    @FXML
    public void onSaveFileMenuItemAction() {
        if (ProgFile.openedProgFile.isAlreadySaved()) {
            try {
                ProgFile.openedProgFile.save();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ExecutionControl.NotImplementedException e) {
                e.printStackTrace();
            }

            StageHelper.changeTitleWindow();
        } else {
            onSaveFileAsMenuItemAction();
        }
    }

    @FXML
    public void onSaveFileAsMenuItemAction() {
        File file = MenuBarHelper.getChooser().showSaveDialog(StageHelper.getPrimaryStage());

        if (file != null) {
            try {
                ProgFile.openedProgFile.save(file.toPath());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExecutionControl.NotImplementedException e) {
                e.printStackTrace();
            }

            StageHelper.changeTitleWindow();
        }
    }

    @FXML
    public void onQuitMenuItemAction() {
        try {
            ProgFile.openedProgFile.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StageHelper.close();
    }

    @FXML
    public void onFullscreenMenuItemAction() {
        StageHelper.changeFullscreen();
        MenuBarHelper.changeFullscreenMenuItemLabel();
    }

    @FXML
    public void onChangeSplitOrientationMenuItemAction() {
        MainHelper.changeSplitPaneOrientation();
    }

    public void onEditorMenuItemAction() {
        MainHelper.changeEditorVisibility();
        MenuBarHelper.changeEditorMenuItemLabel();
    }
}
