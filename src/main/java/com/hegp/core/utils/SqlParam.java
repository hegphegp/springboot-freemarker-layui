package com.hegp.core.utils;

public class SqlParam {
    private String column;
    private String columnType;
    private String paramName; // 前端传过来的参数名称
    private Operator operator;
    private Object value;

    public SqlParam() { }

    public SqlParam(String column, String columnType, String paramName, Operator operator, Object value) {
        this.paramName = paramName;
        this.operator = operator;
        this.column = column;
        this.value = value;
    }

    public static SqlParam of(String column, String columnType, String paramName, Operator operator, Object value) {
        return new SqlParam(column, columnType, paramName, operator, value);
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
