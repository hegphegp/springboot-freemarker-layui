package com.hegp.core.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

public interface Query<T> {

    Query<T> page(int page, int pagesize);

    Query<T> equal(String property, Object value);

    Query<T> notEqual(String property, Object value);

    Query<T> in(String property, Collection<?> value);

    Query<T> in(String property, Object... value);

    Query<T> notIn(String property, Collection<?> value);

    Query<T> notIn(String property, Object... value);

    Query<T> lessOrEqual(String property, Object value);

    Query<T> lessThan(String property, Object value);

    Query<T> greaterOrEqual(String property, Object value);

    Query<T> greaterThan(String property, Object value);

    Query<T> like(String property, String value);

    Query<T> notLike(String property, String value);

    Query<T> isNull(String property);

    Query<T> notNull(String property);

//    Query<T> filters(List<Filter> filters);

//    Query<T> filter(Filter filter);

//    Query<T> filters(Filter... filters);

//    Query<T> filterAnd(Filter... filters);

//    Query<T> filterOr(Filter... filters);

//    Query<T> filterNot(Filter filter);

    Query<T> sort(Sort... sorts);

    Query<T> sort(List<Sort> sorts);

//    Query<T> field(Field field);

//    Query<T> fields(Field... fields);

//    Query<T> fields(List<Field> fields);

    List<T> list();

    Page<T> page();

    T first();

    Long count();
}