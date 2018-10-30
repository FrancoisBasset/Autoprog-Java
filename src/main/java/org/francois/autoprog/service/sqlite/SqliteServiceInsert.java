package org.francois.autoprog.service.sqlite;

import java.sql.SQLException;

public class SqliteServiceInsert extends SqliteService {
    public static void insertControl(String type, String idText) throws SQLException {
        String command = String.format("insert into controls (type, idText) values('%s', '%s')", type, idText);

        execute(command);
    }

    public static void insertPosition(String idText, int x, int y) throws SQLException {
        String command = String.format("insert into positions values('%s', %s, %s)", getControlIdText(idText), x, y);

        execute(command);
    }

    public static void insertSize(String idText, int width, int height) throws SQLException {
        String command = String.format("insert into sizes values('%s', %s, %s)", getControlIdText(idText), width, height);

        execute(command);
    }

    public static void insertControlsValues(String idText, String value) throws SQLException {
        String command = String.format("insert into controlsValues values('%s', '%s')", getControlIdText(idText), value);

        execute(command);
    }

    public static void insertStyle(String idText, String key, String value) throws SQLException {
        String command = String.format("insert into styles values('%s', '%s', '%s')", getControlIdText(idText), key, value);

        execute(command);
    }
}
