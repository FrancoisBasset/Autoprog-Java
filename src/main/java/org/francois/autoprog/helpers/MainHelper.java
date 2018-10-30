package org.francois.autoprog.helpers;

import javafx.geometry.Orientation;
import org.francois.autoprog.controllers.Controllers;

public class MainHelper {
    public static void changeSplitPaneOrientation() {
        if (Controllers.main.workspaceSplitPane.getOrientation() == Orientation.HORIZONTAL) {
            Controllers.main.workspaceSplitPane.setOrientation(Orientation.VERTICAL);
        } else {
            Controllers.main.workspaceSplitPane.setOrientation(Orientation.HORIZONTAL);
        }
    }

    public static boolean isEditorVisible() {
        return Controllers.main.workspaceSplitPane.getItems().size() == 2;
    }

    public static void changeEditorVisibility() {
        if (isEditorVisible()) {
            hideEditor();
        } else {
            Controllers.main.workspaceSplitPane.getItems().add(Controllers.main.editorAnchorPane);
        }
    }

    public static void hideEditor() {
        Controllers.main.workspaceSplitPane.getItems().remove(1);
    }
}
