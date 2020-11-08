package com.hegp.core.utils;

import java.io.Serializable;

public enum ColumnType implements Serializable {
    BOOLEAN("BOOLEAN"),
    INTEGER("INTEGER"),
    LONG("LONG"),
    DOUBLE("DOUBLE"),
    FLOAT("FLOAT"),
    VARCHAR("VARCHAR"),
    NUMBER("NUMBER"),
    DATE("DATE"),
    TIMESTAMP("TIMESTAMP");

    private final String columnType;

    ColumnType(final String columnType) {
        this.columnType = columnType;
    }

    public String getColumnType() {
        return this.columnType;
    }
}
