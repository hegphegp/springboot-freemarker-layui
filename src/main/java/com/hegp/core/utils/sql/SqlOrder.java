package com.hegp.core.utils.sql;

public class SqlOrder {
    private String column;
    private Operator order;

    public SqlOrder() { }

    public SqlOrder(String column, Operator order) {
        this.column = column;
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Operator getOrder() {
        return order;
    }

    public void setOrder(Operator order) {
        this.order = order;
    }
}
