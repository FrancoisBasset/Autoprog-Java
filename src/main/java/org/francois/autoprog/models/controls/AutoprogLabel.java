package org.francois.autoprog.models.controls;

import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoprogLabel extends AutoprogLabeled {
    public AutoprogLabel() {
        super(Label.class);
    }

    public AutoprogLabel(ResultSet control, ResultSet position, ResultSet size, ResultSet styles, ResultSet values) throws SQLException, ClassNotFoundException {
        super(control, position, size, styles, values);
    }

    @Override
    public Object getControl() {
        Label l = new Label(getLabel());
        l.setId(idText);
        l.setLayoutX(x);
        l.setLayoutY(y);
        l.resize(width, height);

        return l;
    }
}
