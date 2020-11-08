package com.hegp.core.utils;

import org.springframework.util.ObjectUtils;

import java.util.*;

public class ConditionWrapper {

    private List<Condition> where = new ArrayList();

    private List<String> groupBy = new ArrayList();
    private List<SqlOrder> orderBy = new ArrayList();

    public ConditionWrapper eq(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.EQ, val);
    }

    public ConditionWrapper ne(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.NE, val);
    }

    public ConditionWrapper gt(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.GT, val);
    }

    public ConditionWrapper ge(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.GE, val);
    }

    public ConditionWrapper lt(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.LT, val);
    }

    public ConditionWrapper le(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.LE, val);
    }

    public ConditionWrapper like(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.LE, val);
    }

    public ConditionWrapper notLike(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.NOT_LIKE, val);
    }

    public ConditionWrapper likeLeft(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.LIKE_LEFT, val);
    }

    public ConditionWrapper likeRight(boolean use, String column, Object val) {
        return addCondition(use, column, Operator.LIKE_RIGHT, val);
    }

    public ConditionWrapper or(boolean use, List<Condition> conditions) {
        return addOrConditions(use, conditions);
    }

    public ConditionWrapper isNull(boolean use, String column) {
        return addCondition(use, column, Operator.IS_NULL, null);
    }

    public ConditionWrapper isNotNull(boolean use, String column) {
        return addCondition(use, column, Operator.IS_NOT_NULL, null);
    }

    public ConditionWrapper in(boolean use, String column, Collection<?> coll) {
        return addCondition(use, column, Operator.IN, coll);
    }

    public ConditionWrapper notIn(boolean use, String column, Collection<?> coll) {
        return addCondition(use, column, Operator.NOT_IN, coll);
    }

    /**
     * 普通查询条件
     * @param use        是否执行
     * @param column     属性
     * @param operator   操作
     * @param val        条件值
     */
    protected ConditionWrapper addCondition(boolean use, String column, Operator operator, Object val) {
        if (use) {
            where.add(Condition.of(column, operator, val));
        }
        return this;
    }

    /**
     * 普通查询条件
     * @param use           是否执行
     * @param conditions    条件值
     */
    protected ConditionWrapper addOrConditions(boolean use, List<Condition> conditions) {
        if (use) {
            where.add(Condition.or(conditions));
        }
        return this;
    }

    public ConditionWrapper orderAsc(String... columns) {
        if (!ObjectUtils.isEmpty(columns)) {
            for (String column:columns) {
                orderBy.add(new SqlOrder(column, Operator.ASC));
            }
        }
        return this;
    }

    public ConditionWrapper orderDesc(String... columns) {
        if (!ObjectUtils.isEmpty(columns)) {
            for (String column:columns) {
                orderBy.add(new SqlOrder(column, Operator.DESC));
            }
        }
        return this;
    }

    /**
     * 排序
     * @param orders
     * @return
     */
    public ConditionWrapper order(SqlOrder... orders) {
        if (!ObjectUtils.isEmpty(orders)) {
            for (SqlOrder order:orders) {
                orderBy.add(order);
            }
        }
        return this;
    }

    public ConditionWrapper group(String... columns) {
        if (!ObjectUtils.isEmpty(columns)) {
            for (String column:columns) {
                groupBy.add(column);
            }
        }
        return this;
    }

    public List<String> getGroupBy() {
        return groupBy;
    }

    public List<SqlOrder> getOrderBy() {
        return orderBy;
    }

}
