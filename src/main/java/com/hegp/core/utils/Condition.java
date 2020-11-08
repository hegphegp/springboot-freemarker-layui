package com.hegp.core.utils;

import java.util.List;

public class Condition {
    private String column;
    private Operator operator; /** =,>=,>,<=,<,like,in,notIn */
    private Object value;
    private Object start;
    private Object end;
    private List<Condition> orConditions;

    public Condition() { }

    public static Condition of(String column, Operator operator, Object value) {
        Condition condition = new Condition();
        condition.column = column;
        condition.operator = operator;
        condition.value = value;
        return condition;
    }

    public static Condition or(List<Condition> conditions) {
        Condition condition = new Condition();
        condition.orConditions = conditions;
        condition.operator = Operator.OR;
        return condition;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Condition> getOrConditions() {
        return orConditions;
    }

    public void setOrConditions(List<Condition> orConditions) {
        this.orConditions = orConditions;
    }

    public Object getStart() {
        return start;
    }

    public void setStart(Object start) {
        this.start = start;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }
}
