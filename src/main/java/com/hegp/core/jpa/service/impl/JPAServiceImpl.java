package com.hegp.core.jpa.service.impl;

import com.hegp.core.config.InitJpaRepositoryConfig;
import com.hegp.core.jpa.service.JPAService;
import com.hegp.core.jpa.service.Query;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//@Transactional
public class JPAServiceImpl<T, ID> implements JPAService<T, ID>, InitializingBean, ApplicationContextAware {
    public EntityManager entityManager;
    public SimpleJpaRepository<T, ID> simpleJpaRepository;
    private ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
//        System.out.println("\n\n\n\n\n setApplicationContext(ApplicationContext applicationContext) \n\n\n\n\n");
//        Class claz = getGenericEntityClass();
//        Map<String, EntityManager> map = applicationContext.getBeansOfType(EntityManager.class);
//        for (Map.Entry<String, EntityManager> entry : map.entrySet()) {
//            Set<EntityType<?>> set = entry.getValue().getMetamodel().getEntities();
//            for (EntityType entityType:set) {
//                if (entityType.getJavaType()==claz && InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(claz) == null) {
//                    synchronized (InitJpaRepositoryConfig.class) {
//                        if (InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(claz) == null) {
//                            entityManager = entry.getValue();
//                            simpleJpaRepository = new SimpleJpaRepository(claz, entityManager);
//                            InitJpaRepositoryConfig.simpleJpaRepositoryMap.put(claz, simpleJpaRepository);
//                            return;
//                        }
//                    }
//                } else {
//                    entityManager = entry.getValue();
//                    simpleJpaRepository = new SimpleJpaRepository(claz, entityManager);
//                    InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(claz);
//                    return;
//                }
//            }
//        }
    }

    // 获取泛型的具体类
    public Class getGenericEntityClass() {
//        方法一
//        Spring的提供工具类,用于获取继承的父类是泛型的信息
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        return resolvableType.getSuperType().getGeneric(0).resolve();

//        方法二
//        Type type = getClass().getGenericSuperclass();
//        if (type instanceof ParameterizedType) {
//            ParameterizedType pType = (ParameterizedType) type;
//            Type claz = pType.getActualTypeArguments()[0];
//            if (claz instanceof Class) {
//                return (Class) claz;
//            }
//        }
//        return null;
    }

    @Override
    public T find(ID id) {
        return null;
    }

    @Override
    public T save(T entity) {
        simpleJpaRepository.save(entity);
        return null;
    }

    @Override
    public List<T> save(T[] entities) {
        return null;
    }

    @Override
    public List<T> save(List<T> entities) {
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public List<T> update(T[] entities) {
        return null;
    }

    @Override
    public List<T> update(List<T> entities) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void delete(T[] entities) {

    }

    @Override
    public void delete(List<T> entities) {

    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public void deleteById(ID[] ids) {

    }

    @Override
    public void deleteById(List<ID> ids) {

    }

    @Override
    public Query<T> query() {
        return null;
    }

    @Override
    public SimpleJpaRepository<T, ID> getSimpleJpaRepository() {
        return simpleJpaRepository;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public SimpleJpaRepository<T, ID> getSimpleJpaRepository(Class clazz) {
        return InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(clazz);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Class claz = getGenericEntityClass();
        Map<String, EntityManager> map = applicationContext.getBeansOfType(EntityManager.class);
        for (Map.Entry<String, EntityManager> entry : map.entrySet()) {
            Set<EntityType<?>> set = entry.getValue().getMetamodel().getEntities();
            for (EntityType entityType:set) {
                if (entityType.getJavaType()==claz) {
                    if (InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(claz) == null) {
                        synchronized (InitJpaRepositoryConfig.class) {
                            if (InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(claz) == null) {
                                entityManager = entry.getValue();
                                simpleJpaRepository = new SimpleJpaRepository(claz, entityManager);
                                InitJpaRepositoryConfig.simpleJpaRepositoryMap.put(claz, simpleJpaRepository);
                            }
                        }
                    } else {
                        entityManager = entry.getValue();
                        simpleJpaRepository = InitJpaRepositoryConfig.simpleJpaRepositoryMap.get(claz);
                    }
//                    new JpaRepositoryFactory(entityManager).re
                    new JpaRepositoryFactory(entityManager);
                    new JpaRepositoryFactory(entityManager).getEntityInformation(claz);
                    return;
                }
            }
        }
//        System.out.println("\n\n\n\n\n afterPropertiesSet() \n\n\n\n\n");
    }
}
