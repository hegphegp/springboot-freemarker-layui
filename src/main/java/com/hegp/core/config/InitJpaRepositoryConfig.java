package com.hegp.core.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class InitJpaRepositoryConfig implements ApplicationContextAware {
	public static final Map<Class, SimpleJpaRepository> simpleJpaRepositoryMap = new HashMap<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		initJpaRepository(applicationContext);
	}

	public void initJpaRepository(ApplicationContext applicationContext) {
		Map<String, EntityManager> map = applicationContext.getBeansOfType(EntityManager.class);
		for (String key:map.keySet()) {
			Set<EntityType<?>> set = map.get(key).getMetamodel().getEntities();
			for (EntityType entityType:set) {
				simpleJpaRepositoryMap.put(entityType.getJavaType(), new SimpleJpaRepository(entityType.getJavaType(), map.get(key)));
			}
		}
	}

//	private void initJpaRepository(ApplicationContext applicationContext) {
////		Map<String, EntityManager> map = applicationContext.getBeansOfType(EntityManager.class);
//		// 查找所有bean当中，包含NoRepositoryBean注解的bean
//
//		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(NoRepositoryBean.class);
//		for (String key:beans.keySet()) {
//			try {
//				Object targetObject = getTargetObject(beans.get(key));
//				if (targetObject.getClass() == SimpleJpaRepository.class) {
//					Field field = targetObject.getClass().getDeclaredField("entityInformation");
//					field.setAccessible(true);
//					if (JpaEntityInformation.class == field.getType()) {
//						simpleJpaRepositoryMap.put(((JpaEntityInformation)field.get(targetObject)).getJavaType(), (SimpleJpaRepository)targetObject);
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException(e);
//			}
//		}
//	}
//
//	/** 获取代理对象的真实对象 */
//	public static Object getTargetObject(Object proxy) throws Exception {
//		if(!AopUtils.isAopProxy(proxy)) {
//			return proxy;//不是代理对象
//		}
//		if(AopUtils.isJdkDynamicProxy(proxy)) {
//			return getJdkDynamicProxyTargetObject(proxy);
//		} else {
//			return getCglibProxyTargetObject(proxy);
//		}
//	}
//
//	private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
//		Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
//		h.setAccessible(true);
//		Object dynamicAdvisedInterceptor = h.get(proxy);
//		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
//		advised.setAccessible(true);
//		return ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
//	}
//
//	private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
//		Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
//		h.setAccessible(true);
//		AopProxy aopProxy = (AopProxy) h.get(proxy);
//		Field advised = aopProxy.getClass().getDeclaredField("advised");
//		advised.setAccessible(true);
//		return ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();
//	}
}