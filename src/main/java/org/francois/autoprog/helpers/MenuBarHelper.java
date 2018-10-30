package org.francois.autoprog.helpers;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.francois.autoprog.controllers.Controllers;

public class MenuBarHelper {
    private static final String ACTIVER_LE_PLEIN_ECRAN = "Activer le plein écran";
    private static final String DESACTIVER_LE_PLEIN_ECRAN = "Désactiver le plein écran";
    private static final String AFFICHER_EDITEUR = "Afficher l'éditeur";
    private static final String CACHER_EDITEUR = "Cacher l'éditeur";

    public static void changeFullscreenMenuItemLabel() {
        if (StageHelper.isFullscreen()) {
            Controllers.menuBar.fullscreenMenuItem.setText(DESACTIVER_LE_PLEIN_ECRAN);
        } else {
            Controllers.menuBar.fullscreenMenuItem.setText(ACTIVER_LE_PLEIN_ECRAN);
        }
    }

    public static void changeEditorMenuItemLabel() {
        if (MainHelper.isEditorVisible()) {
            Controllers.menuBar.editorMenuItem.setText(CACHER_EDITEUR);
        } else {
            Controllers.menuBar.editorMenuItem.setText(AFFICHER_EDITEUR);
        }
    }

    public static FileChooser getChooser() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Autoprog", "*.prog"));

        return chooser;
    }
}
