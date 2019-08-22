package com.hegp.core.jpa.service;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import javax.persistence.EntityManager;

public interface JPAService<T, ID> {

    T find(ID id);

    T save(T entity);

    List<T> save(T[] entities);

    List<T> save(List<T> entities);

    T update(T entity);

    List<T> update(T[] entities);

    List<T> update(List<T> entities);

    void delete(T entity);

    void delete(T[] entities);

    void delete(List<T> entities);

    void deleteById(ID id);

    void deleteById(ID[] ids);

    void deleteById(List<ID> ids);

    Query<T> query();

    SimpleJpaRepository<T, ID> getSimpleJpaRepository();

    SimpleJpaRepository<T, ID> getSimpleJpaRepository(Class clazz);

    EntityManager getEntityManager();
}
