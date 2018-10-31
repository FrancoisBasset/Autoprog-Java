package org.francois.autoprog.models.controls;

import javafx.scene.Node;
import org.francois.autoprog.controllers.Controllers;
import org.francois.autoprog.service.sqlite.SqliteServiceInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class AutoprogControl<T> {
    protected Class t;
    protected String idText = "";
    protected int x = 0;
    protected int y = 0;
    protected int width = 50;
    protected int height = 50;

    private Node node;
    protected boolean isSaved = false;
    protected boolean isNew;

    //todo optimiser méthode save en updateInWindow et updateInBase, saveInBase

    protected HashMap<String, String> styles = new HashMap<>();

    public AutoprogControl(Class t) {
        this.t = t;
        isNew = true;
    }

    public AutoprogControl(ResultSet control, ResultSet position, ResultSet size, ResultSet styles) throws SQLException, ClassNotFoundException {
        t = Class.forName(control.getString("type"));
        idText = control.getString("idText");

        x = position.getInt("x");
        y = position.getInt("y");

        width = size.getInt("width");
        height = size.getInt("height");

        while (styles.next()) {
            this.styles.put(styles.getString("key"), styles.getString("value"));
        }

        isSaved = true;
        isNew = false;
    }

    public Class getControlType() {
        return t;
    }

    public String getId() {
        return idText;
    }

    public void setId(String idText) {
        this.idText = idText;
        isSaved = false;
        updateControlOnWindow();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        isSaved = false;
        updateControlOnWindow();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        isSaved = false;
        updateControlOnWindow();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        isSaved = false;
        updateControlOnWindow();
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        isSaved = false;
        updateControlOnWindow();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        isSaved = false;
        updateControlOnWindow();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        isSaved = false;
        updateControlOnWindow();
    }

    public HashMap<String, String> getStyles() {
        return styles;
    }

    public void addStyle(String key, String value) {
        if (styles.containsKey(key)) {
            styles.remove(key);
        }

        styles.put(key, value);
        isSaved = false;
        updateControlOnWindow();
    }

    public void removeStyle(String key) {
        if (styles.containsKey(key)) {
            styles.remove(key);
            isSaved = false;
            updateControlOnWindow();
        }
    }

    public void saveControl() throws SQLException {
        if (isNew) {
            saveNewControlInBase();
        } else {
            updateControlInBase();
        }
    }

    public void saveNewControlInBase() throws SQLException {
        SqliteServiceInsert.insertControl(t.getName(), idText);
        SqliteServiceInsert.insertPosition(idText, x, y);
        SqliteServiceInsert.insertSize(idText, width, height);

        for (String key : styles.keySet()) {
            SqliteServiceInsert.insertStyle(idText, key, styles.get(key));
        }

        saveValues();

        isSaved = true;
        isNew = false;
    }

    public void updateControlInBase() {
        System.out.println("cannot update base");

        isSaved = true;
    }

    protected void updateControlOnWindow() {
        eraseControlOnWindow();

        node = (Node) getControl();
        Controllers.main.applicationAnchorPane.getChildren().add(node);
    }

    public void eraseControlOnWindow() {
        if (node != null) {
            Controllers.main.applicationAnchorPane.getChildren().remove(node);
        }
    }

    public abstract T getControl();

    public abstract void saveValues() throws SQLException;
}
