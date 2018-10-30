package org.francois.autoprog.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.francois.autoprog.helpers.StageHelper;
import org.francois.autoprog.models.controls.AutoprogButton;
import org.francois.autoprog.models.controls.AutoprogControl;
import org.francois.autoprog.models.controls.AutoprogLabel;
import org.francois.autoprog.models.controls.AutoprogTextField;
import org.francois.autoprog.models.exceptions.AlreadyExistingIdControlException;
import org.francois.autoprog.models.progfile.ProgFile;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController {
    @FXML
    public AnchorPane root;
    @FXML
    public AnchorPane menuBarAnchorPane;
    @FXML
    public SplitPane workspaceSplitPane;
    @FXML
    public AnchorPane applicationAnchorPane;
    @FXML
    public AnchorPane editorAnchorPane;

    @FXML
    Button bouton;
    @FXML
    Button label;
    @FXML
    Button textfield;

    public void initialize() {
        Controllers.main = this;

        try {
            ProgFile.openedProgFile = new ProgFile();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StageHelper.changeTitleWindow();
    }

    int b, l, t = 1;
    Random r = new Random();

    @FXML
    public void ajoutBouton(MouseEvent mouseEvent) {
        System.out.println("ok");

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            AutoprogButton button = new AutoprogButton();
            button.setId(String.valueOf("id_" + String.valueOf(b)));
            button.setLabel(String.valueOf(b));
            button.setX(r.nextInt(200));
            button.setY(r.nextInt(200));
            button.setSize(r.nextInt(50) + 10, r.nextInt(50) + 10);

            try {
                ProgFile.openedProgFile.addControl(button);
            } catch (AlreadyExistingIdControlException e) {
                e.printStackTrace();
            }

            b++;
        }

        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            Stream s = ProgFile.openedProgFile.getControls().stream().filter(c -> c.getControlType().equals(Button.class));

            while (s.iterator().hasNext()) {
                AutoprogControl ac = ((AutoprogControl) s.iterator().next());

                ac.eraseControlOnWindow();
                ProgFile.openedProgFile.removeControl(ac);
            }
        }
    }

    @FXML
    public void ajoutLabel(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            AutoprogLabel label = new AutoprogLabel();
            label.setId(String.valueOf("id_" + String.valueOf(b)));
            label.setLabel(String.valueOf(l));
            label.setX(r.nextInt(200));
            label.setY(r.nextInt(200));
            label.setSize(r.nextInt(50) + 10, r.nextInt(50) + 10);

            try {
                ProgFile.openedProgFile.addControl(label);
            } catch (AlreadyExistingIdControlException e) {
                e.printStackTrace();
            }
        }

        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            for (AutoprogControl c : ProgFile.openedProgFile.getControls()) {
                if (c.getControlType().equals(Label.class)) {
                    c.eraseControlOnWindow();
                    ProgFile.openedProgFile.removeControl(c);
                }
            }
        }
    }

    @FXML
    public void ajoutTextfield(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            AutoprogTextField textField = new AutoprogTextField();
            textField.setId(String.valueOf("id_" + String.valueOf(b)));
            textField.setLabel(String.valueOf(t));
            textField.setX(r.nextInt(200));
            textField.setY(r.nextInt(200));
            textField.setSize(r.nextInt(50) + 10, r.nextInt(50) + 10);

            try {
                ProgFile.openedProgFile.addControl(textField);
            } catch (AlreadyExistingIdControlException e) {
                e.printStackTrace();
            }
        }

        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            for (AutoprogControl c : ProgFile.openedProgFile.getControls()) {
                if (c.getControlType().equals(TextField.class)) {
                    c.eraseControlOnWindow();
                    ProgFile.openedProgFile.removeControl(c);
                }
            }
        }
    }
}
