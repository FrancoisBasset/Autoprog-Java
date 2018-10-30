package org.francois.autoprog.models.sqlite;

public enum SqliteTables {
    CONTROLS("controls", SqliteAttributes.ID, SqliteAttributes.TYPE, SqliteAttributes.IDTEXT),
    POSITIONS("positions", SqliteAttributes.CONTROL, SqliteAttributes.X, SqliteAttributes.Y),
    SIZES("sizes", SqliteAttributes.CONTROL, SqliteAttributes.WIDTH, SqliteAttributes.HEIGHT),
    STYLES("styles", SqliteAttributes.CONTROL, SqliteAttributes.KEY, SqliteAttributes.VALUE),
    CONTROLSVALUES("controlsValues", SqliteAttributes.CONTROL, SqliteAttributes.VALUE);

    private String tableName;
    private SqliteAttribute[] attributes;

    SqliteTables(String tableName, SqliteAttribute... attributes) {
        this.tableName = tableName;
        this.attributes = attributes;
    }

    public String getTableName() {
        return tableName;
    }

    public SqliteAttribute[] getAttributes() {
        return attributes;
    }
}
