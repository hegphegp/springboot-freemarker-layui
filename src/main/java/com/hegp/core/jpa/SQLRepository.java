package com.hegp.core.jpa;

import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SQLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Map> assemblyPageData(String dataSQL, String countSQL, int page, int pagesize, Object... params) {
        if (pagesize<1) {
            throw new RuntimeException("pagesize必须大于0");
        }
        Long totalCount = queryResultCount(countSQL, params);
        List<Map> data = new ArrayList<>();
        if (totalCount!=0) {
            data = queryResultList(dataSQL, page, pagesize, params);
        }
        return new PageImpl(data, PageRequest.of(page, pagesize), totalCount);
    }

    /** 前端传过来的页码是从1开始的，entityManager.createNativeQuery的查询的*/
    public List<Map> queryResultList(String sql, int page, int pagesize, Object... params) {
        Query dataQuery = entityManager.createNativeQuery(sql);
        dataQuery = assemblyParam(dataQuery, params);
        dataQuery.unwrap(NativeQueryImpl.class)
                .setFirstResult((page-1)*pagesize)
                .setMaxResults(pagesize);
        return (List<Map>) dataQuery.getResultList()
                .stream().map(item -> convertKeyToCamel((Map<String, Object>) item))
                .collect(Collectors.toList());
    }

    /** 装配Sql,返回全部结果 */
    public List<Map> queryResultList(String sql, Object... params) {
        Query dataQuery = entityManager.createNativeQuery(sql);
        dataQuery = assemblyParam(dataQuery, params);
        dataQuery.unwrap(NativeQueryImpl.class);
        return (List<Map>) dataQuery.getResultList()
                .stream().map(item -> convertKeyToCamel((Map<String, Object>) item))
                .collect(Collectors.toList());
    }

    public Map queryResultMap(String sql, Object... params) {
        List<Map> data = queryResultList(sql, params);
        if(data.size() != 0) {
            return data.get(0);
        }
        return null;
    }

    /** 返回SQL查询的数据条数 */
    public Long queryResultCount(String sql, Object... params) {
        Query countQuery = entityManager.createNativeQuery(sql, Long.class);
        countQuery = assemblyParam(countQuery, params);
        return (Long) countQuery.getSingleResult();
    }

    /** 执行修改语句，返回受影响行数 */
    public int queryModifyCount(String sql, Object... params){
        EntityTransaction transaction = entityManager.getTransaction();
        Query dataQuery = entityManager.createNativeQuery(sql);
        dataQuery = assemblyParam(dataQuery,params);
        int count = dataQuery.executeUpdate();
        transaction.commit();
        return count;
    }

    /** 为Sql填充参数 */
    private static Query assemblyParam(Query query, Object... params) {
        int index = 1;
        for (Object param : params) {
            if (param!=null && param instanceof Collection) {
                Collection collection = (Collection)param;
                if (collection.size()>0 && collection.iterator().next() instanceof Number) {
                    query.setParameter(index, assemblyNumberArrInSQL(collection));
                } else if (collection.size()>0 && collection.iterator().next() instanceof String) {
                    query.setParameter(index, assemblyStrArrInSQL(collection));
                }
            } else if (!StringUtils.isEmpty(param)) {
                query.setParameter(index, param);
            }
            index++;
        }
        return query;
    }

    /**
     * 封装SQL语句中in查询的参数格式
     * 集合参数为空，返回空字符串
     * 集合参数非空，如果参数是[id1,id2,id3,id4]，返回的结果是 ('id1','id2','id3','id4')
     */
    public static String assemblyStrArrInSQL(Collection<String> collection) {
        if (collection==null || collection.size()==0) {
            return "";
        }
        return "'"+String.join("','", collection)+"'";
    }

    /**
     * 封装SQL语句中in查询的参数格式
     * 集合参数为空，返回空字符串
     * 集合参数非空，如果参数是[id1,id2,id3,id4]，返回的结果是 ('id1','id2','id3','id4')
     */
    public static String assemblyNumberArrInSQL(Collection<Number> collection) {
        if (collection==null || collection.size()==0) {
            return "";
        }
        Iterator<Number> iterator = collection.iterator();
        StringBuffer sb = new StringBuffer(iterator.next()+"");
        while (iterator.hasNext()) {
            sb.append(","+iterator.next());
        }
        return sb.toString();
    }

    /**
     * 把map的key转换为驼峰命名
     * @param map map对象
     * @return 返回转换后的值
     */
    private static Map convertKeyToCamel(Map map){
        if(map==null) {
            return null;
        }
        Map linkedHashMap = new LinkedHashMap();
        map.forEach((key, value) -> linkedHashMap.put(convert(key.toString()), value));
        return linkedHashMap;
    }

    /**
     * 数据库查出来的字段，下划线转驼峰
     * @param defaultName
     * @return
     */
    private static String convert(String defaultName) {
        char[] arr = defaultName.toCharArray();
        StringBuilder nameToReturn = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '_') {
                nameToReturn.append(Character.toUpperCase(arr[++i]));
            } else {
                nameToReturn.append(arr[i]);
            }
        }
        return nameToReturn.toString();
    }
}
