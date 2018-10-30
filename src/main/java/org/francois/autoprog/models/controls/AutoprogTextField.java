package org.francois.autoprog.models.controls;

import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoprogTextField extends AutoprogLabeled {
    public AutoprogTextField() {
        super(TextField.class);
    }

    public AutoprogTextField(ResultSet control, ResultSet position, ResultSet size, ResultSet styles, ResultSet values) throws SQLException, ClassNotFoundException {
        super(control, position, size, styles, values);
    }

    @Override
    public Object getControl() {
        TextField tf = new TextField(getLabel());
        tf.setId(idText);
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.resize(width, height);

        return tf;
    }
}
