package org.francois.autoprog.models.sqlite;

public class SqliteAttribute {
    private String name;
    private SqliteAttributeType type;
    private boolean notNull;
    private boolean primaryKey;
    private boolean autoIncrement;
    private boolean unique;
    private String defaultValue;

    public SqliteAttribute(String name, SqliteAttributeType type) {
        this(name, type, true, false, false, false, "");
    }

    public SqliteAttribute(String name, SqliteAttributeType type, boolean notNull, boolean primaryKey, boolean autoIncrement, boolean unique, String defaultValue) {
        this.name = name;
        this.type = type;
        this.notNull = notNull;
        this.primaryKey = primaryKey;
        this.autoIncrement = autoIncrement;
        this.unique = unique;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public SqliteAttributeType getType() {
        return type;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public boolean isUnique() {
        return unique;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getCommandForm() {
        StringBuilder commmandForm = new StringBuilder(name);
        commmandForm.append(" ").append(type).append(" ");

        if (notNull) {
            commmandForm.append("NOT NULL ");
        }

        if (primaryKey) {
            commmandForm.append("PRIMARY KEY ");
        }

        if (autoIncrement) {
            commmandForm.append("AUTOINCREMENT ");
        }

        if (unique) {
            commmandForm.append("UNIQUE");
        }

        return commmandForm.toString();
    }
}
