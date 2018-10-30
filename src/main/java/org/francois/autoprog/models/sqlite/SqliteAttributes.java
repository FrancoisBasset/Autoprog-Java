package org.francois.autoprog.models.sqlite;

public class SqliteAttributes {
    public static final SqliteAttribute ID = new SqliteAttribute("id", SqliteAttributeType.INTEGER, true, true, true, false, "");
    public static final SqliteAttribute CONTROL = new SqliteAttribute("control", SqliteAttributeType.INTEGER);
    public static final SqliteAttribute TYPE = new SqliteAttribute("type", SqliteAttributeType.TEXT);
    public static final SqliteAttribute IDTEXT = new SqliteAttribute("idText", SqliteAttributeType.TEXT);
    public static final SqliteAttribute X = new SqliteAttribute("x", SqliteAttributeType.INTEGER);
    public static final SqliteAttribute Y = new SqliteAttribute("y", SqliteAttributeType.INTEGER);
    public static final SqliteAttribute WIDTH = new SqliteAttribute("width", SqliteAttributeType.INTEGER);
    public static final SqliteAttribute HEIGHT = new SqliteAttribute("height", SqliteAttributeType.INTEGER);
    public static final SqliteAttribute KEY = new SqliteAttribute("key", SqliteAttributeType.TEXT);
    public static final SqliteAttribute VALUE = new SqliteAttribute("value", SqliteAttributeType.TEXT);
}
