package com.hegp.core.jpa.service.impl;

import com.hegp.core.config.InitJpaRepositoryConfig;
import com.hegp.core.jpa.service.JPAService;
import com.hegp.core.jpa.service.Query;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class JPAServiceImpl<T, ID> implements JPAService<T, ID>, InitializingBean, ApplicationContextAware {
    public SimpleJpaRepository<T, ID> simpleJpaRepository;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Class<T> dc = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        System.out.println("\n\n\n\n\n\n\n\n\n"+dc.getClass()+"\n\n\n\n\n\n\n\n\n\n\n\n\n");
        simpleJpaRepository = InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(dc);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public T find(Object o) {
        return null;
    }

    @Override
    public T save(Object entity) {
        return null;
    }

    @Override
    public List<T> save(Object[] entities) {
        return null;
    }

    @Override
    public T update(Object entity) {
        return null;
    }

    @Override
    public List<T> update(Object[] entities) {
        return null;
    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void delete(Object[] entities) {

    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void deleteById(Object[] objects) {

    }

    @Override
    public Query<T> query() {
        return null;
    }

    @Override
    public EntityManager getEntityManager() {
        return null;
    }
}
