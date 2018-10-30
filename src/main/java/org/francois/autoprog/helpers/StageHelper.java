package org.francois.autoprog.helpers;

import javafx.stage.Stage;
import org.francois.autoprog.models.progfile.ProgFile;

public class StageHelper {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void close() {
        primaryStage.close();
    }

    public static void changeFullscreen() {
        primaryStage.setFullScreen(!isFullscreen());
    }

    public static boolean isFullscreen() {
        return primaryStage.isFullScreen();
    }

    public static void changeTitleWindow() {
        primaryStage.setTitle(ProgFile.openedProgFile.getFileName() + " - Autoprog");
    }
}
