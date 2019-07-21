package com.hegp.core.jpa.service.impl;

import com.hegp.core.jpa.service.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

public class QueryImpl<T> implements Query<T> {

    @Override
    public Query<T> page(int page, int pagesize) {
        return null;
    }

    @Override
    public Query<T> equal(String property, Object value) {
        return null;
    }

    @Override
    public Query<T> notEqual(String property, Object value) {
        return null;
    }

    @Override
    public Query<T> in(String property, Collection<?> value) {
        return null;
    }

    @Override
    public Query<T> in(String property, Object... value) {
        return null;
    }

    @Override
    public Query<T> notIn(String property, Collection<?> value) {
        return null;
    }

    @Override
    public Query<T> notIn(String property, Object... value) {
        return null;
    }

    @Override
    public Query<T> lessOrEqual(String property, Object value) {
        return null;
    }

    @Override
    public Query<T> lessThan(String property, Object value) {
        return null;
    }

    @Override
    public Query<T> greaterOrEqual(String property, Object value) {
        return null;
    }

    @Override
    public Query<T> greaterThan(String property, Object value) {
        return null;
    }

    @Override
    public Query<T> like(String property, String value) {
        return null;
    }

    @Override
    public Query<T> notLike(String property, String value) {
        return null;
    }

    @Override
    public Query<T> isNull(String property) {
        return null;
    }

    @Override
    public Query<T> notNull(String property) {
        return null;
    }

    @Override
    public Query<T> sort(Sort... sorts) {
        return null;
    }

    @Override
    public Query<T> sort(List<Sort> sorts) {
        return null;
    }

    @Override
    public List<T> list() {
        return null;
    }

    @Override
    public Page<T> page() {
        return null;
    }

    @Override
    public T first() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
