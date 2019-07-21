package com.hegp.core.jpa.service;

import java.util.List;
import javax.persistence.EntityManager;

public interface JPAService<T, ID> {

    T find(ID id);

    T save(T entity);

    List<T> save(T[] entities);

    T update(T entity);

    List<T> update(T[] entities);

    void delete(T entity);

    void delete(T[] entities);

    void deleteById(ID id);

    void deleteById(ID[] ids);

    Query<T> query();

    EntityManager getEntityManager();
}
