package org.francois.autoprog.service.sqlite;

import org.francois.autoprog.models.sqlite.SqliteAttribute;

import java.sql.SQLException;

public class SqliteServiceCreate extends SqliteService {
    public static void createTable(String tableName, SqliteAttribute[] attributes) throws SQLException {
        StringBuilder command = new StringBuilder("create table ");
        command.append(tableName).append(" (");

        for (SqliteAttribute attribute : attributes) {
            command.append(attribute.getCommandForm()).append(", ");
        }

        command.deleteCharAt(command.lastIndexOf(","));
        command.append(")");

        execute(command.toString());
    }
}
