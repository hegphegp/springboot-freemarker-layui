package com.hegp.core.utils.sql;

import java.io.Serializable;

public enum Operator implements Serializable {
    OR("OR"),
    IN("IN"),
    NOT_IN("NOT_IN"),
    LIKE("LIKE"),
    NOT_LIKE("NOT_LIKE"),
    LIKE_LEFT("LIKE_LEFT"),
    LIKE_RIGHT("LIKE_RIGHT"),
    EQ("="),
    NE("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    HAVING("HAVING"),
    ASC("ASC"),
    DESC("DESC");

    private final String operator;

    Operator(final String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }
}
