package org.francois.autoprog.forms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.francois.autoprog.helpers.StageHelper;
import org.francois.autoprog.models.progfile.ProgFile;

import java.io.IOException;
import java.sql.SQLException;

public class MainForm extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        StageHelper.setPrimaryStage(primaryStage);

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.getIcons().add(new Image("/icons/autoprogIcon.png"));
        primaryStage.setFullScreenExitHint("");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            try {
                ProgFile.openedProgFile.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }
}
