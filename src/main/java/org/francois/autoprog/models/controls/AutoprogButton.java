package org.francois.autoprog.models.controls;

import javafx.scene.control.Button;
import org.francois.autoprog.service.sqlite.SqliteServiceInsert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoprogButton extends AutoprogLabeled {
    public AutoprogButton() {
        super(Button.class);
    }

    public AutoprogButton(ResultSet control, ResultSet position, ResultSet size, ResultSet styles, ResultSet values) throws SQLException, ClassNotFoundException {
        super(control, position, size, styles, values);
    }

    @Override
    public Object getControl() {
        Button b = new Button(getLabel());
        b.setId(idText);
        b.setLayoutX(x);
        b.setLayoutY(y);
        b.resize(width, height);

        return b;
    }
}
