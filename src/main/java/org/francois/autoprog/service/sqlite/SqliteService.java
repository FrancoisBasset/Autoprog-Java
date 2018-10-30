package org.francois.autoprog.service.sqlite;

import org.francois.autoprog.models.sqlite.SqliteTables;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteService {
    private static Connection connection;

    public static void connectToDatabase(Path path) {
        String url = String.format("jdbc:sqlite:%s", path.toAbsolutePath());

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }

    public static void createTables() throws SQLException {
        for (SqliteTables table : SqliteTables.values()) {
            SqliteServiceCreate.createTable(table.getTableName(), table.getAttributes());
        }
    }

    protected static void execute(String command) throws SQLException {
        connection.createStatement().execute(command);
    }

    protected static ResultSet executeQuery(String command) throws SQLException {
        return connection.createStatement().executeQuery(command);
    }

    protected static int getControlIdText(String idText) throws SQLException {
        String command = String.format("select id from controls where idText = '%s'", idText);

        return executeQuery(command).getInt("id");
    }

    public static int getNumberOfControls() throws SQLException {
        return executeQuery("select count(*) from controls").getInt("count(*)");
    }
}
