package org.francois.autoprog.service.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteServiceSelect extends SqliteService {
    public static List<Integer> getAllControlsId() throws SQLException {
        List<Integer> ids = new ArrayList<>();

        ResultSet set = executeQuery("select id from controls");

        while (set.next()) {
            ids.add(set.getInt("id"));
        }

        return ids;
    }

    public static List<ResultSet> selectControl(int id) throws SQLException {
        List<ResultSet> sets = new ArrayList<>();

        sets.add(executeQuery("select * from controls where id = " + id));
        sets.add(executeQuery("select * from positions where control = " + id));
        sets.add(executeQuery("select * from sizes where control = " + id));
        sets.add(executeQuery("select * from controlsValues where control = " + id));
        sets.add(executeQuery("select * from styles where control = " + id));

        return sets;
    }

    public static Class getClassOfControl(List<ResultSet> sets) throws SQLException, ClassNotFoundException {
        return Class.forName(sets.get(0).getString("type"));
    }
}
