package com.hegp;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

public class List2TreeList {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add(new HashMap(){{put("id", 5); put("pid", 4); put("name", "昌平区"); put("sort", 5); }});
        list.add(new HashMap(){{put("id", 4); put("pid", 0); put("name", "北京市"); put("sort", 4); }});
        list.add(new HashMap(){{put("id", 3); put("pid", 2); put("name", "秦州区"); put("sort", 3); }});
        list.add(new HashMap(){{put("id", 2); put("pid", 0); put("name", "北京市"); put("sort", 4); }});
        list.add(new HashMap(){{put("id", 1); put("pid", 0); put("name", "甘肃省"); put("sort", 1); }});
        System.out.println(JSON.toJSONString(asc(list, "id", "pid", "children", "sort")));
        System.out.println(JSON.toJSONString(desc(list, "id", "pid", "children", "sort")));
    }

    /**
     * @param list
     * @param idField
     * @param parentIdField
     * @param childrenField
     * @param sortField 排序列可以为空,不为空必须是数字类型
     * @param <T>
     * @return
     */
    public static <T> List<T> asc(List<T> list, String idField, String parentIdField, String childrenField, String sortField) {
        List<T> root = new ArrayList<>();
        Map<Object, List<T>> childrenMap = new HashMap<>();
        assemblyList(list, idField, parentIdField, childrenField, root, childrenMap);
        if (list==null || list.size()==0 || StringUtils.isEmpty(sortField)) return root;
        if (list.get(0) instanceof Map) {
            for (Object key : childrenMap.keySet()) {
                sortAscMap((List<Map<Object, Object>>)childrenMap.get(key), sortField);
            }
            sortAscMap((List<Map<Object, Object>>)root, sortField);
        } else {
            for (Object key : childrenMap.keySet()) {
                sortAsc(childrenMap.get(key), sortField);
            }
            sortAsc(root, sortField);
        }
        return root;
    }

    /**
     * @param list
     * @param idField
     * @param parentIdField
     * @param childrenField
     * @param sortField 排序列必须是数字类型
     * @param <T>
     * @return
     */
    public static <T> List<T> desc(List<T> list, String idField, String parentIdField, String childrenField, String sortField) {
        List<T> root = new ArrayList<>();
        Map<Object, List<T>> childrenMap = new HashMap<>();
        assemblyList(list, idField, parentIdField, childrenField, root, childrenMap);
        if (list==null || list.size()==0 || StringUtils.isEmpty(sortField)) return root;
        if (list.get(0) instanceof Map) {
            for (Object key : childrenMap.keySet()) {
                sortDescMap((List<Map<Object, Object>>)childrenMap.get(key), sortField);
            }
            sortDescMap((List<Map<Object, Object>>)root, sortField);
        } else {
            for (Object key:childrenMap.keySet()) {
                sortDesc(childrenMap.get(key), sortField);
            }
            sortDesc(root, sortField);
        }
        return root;
    }

    private static <T> void assemblyList(List<T> list, String idField, String parentIdField, String childrenField, List<T> root, Map<Object, List<T>> childrenMap) {
        // 非空校验,冗余的垃圾校验代码
        if (ObjectUtils.isEmpty(list)) return;
        list.remove(null);
        if (ObjectUtils.isEmpty(list)) return;

        if (list.get(0) instanceof Map) {
            assemblyListMap(list, idField, parentIdField, childrenField, root, childrenMap);
        } else {
            assemblyListObject(list, idField, parentIdField, childrenField, root, childrenMap);
        }
    }

    private static <T> void assemblyListMap(List<T> list, String idField, String parentIdField, String childrenField, List<T> root, Map<Object, List<T>> childrenMap) {
        List<Map> rootList = new ArrayList<>();
        List<Map> listMap = (List<Map>)list;
        Map<Object, List<Map>> childrenMapObject = new HashMap<>();
        Map<Object, Map> map = new HashMap<>();
        for (Map t : listMap) {
            map.put(t.get(idField), t);
        }
        for (Map t : listMap) {
            Map parent = map.get(t.get(parentIdField));
            if (parent == null) {
                rootList.add(t);
            } else {
                notExistsThenAdd(childrenMapObject, t, parentIdField, t);
            }
        }
        for (Object key:childrenMapObject.keySet()) {
            map.get(key).put(childrenField, childrenMapObject.get(key));
            childrenMap.put(key, (List<T>)childrenMapObject.get(key));
        }
        root.addAll((List<T>)rootList);
    }

    private static <T> void assemblyListObject(List<T> list, String idField, String parentIdField, String childrenField, List<T> root, Map<Object, List<T>> childrenMap) {
        Map<Object, T> map = new HashMap<>();
        for (T t : list) {
            map.put(BeanMap.create(t).get(idField), t);
        }
        for (T t : list) {
            BeanMap beanMap = BeanMap.create(t);
            T parent = map.get(beanMap.get(parentIdField));
            if (parent == null) {
                root.add(t);
            } else {
                List<T> children = notExistsThenAdd(childrenMap, beanMap, parentIdField, t);
                BeanMap.create(parent).put(childrenField, children);
            }
        }
    }

    private static <T> List<T> notExistsThenAdd(Map<Object, List<T>> mapList, Map map, String parentIdField, T item) {
        List<T> children = mapList.get(map.get(parentIdField));
        if (ObjectUtils.isEmpty(children)) {
            children = new ArrayList<>();
            mapList.put(map.get(parentIdField), children);
        }
        children.add(item);
        return children;
    }

    private static <T> void sortAsc(List<T> list, String sortField) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return compareAsc((Number)BeanMap.create(o1).get(sortField), (Number)BeanMap.create(o2).get(sortField));
            }
        });
    }

    private static int compareAsc(Number number1, Number number2) {
        if (number1==null) return -1;
        if (number2==null) return 1;
        BigDecimal number1Value = new BigDecimal(number1.toString());
        BigDecimal number2Value = new BigDecimal(number2.toString());
        return number1Value.compareTo(number2Value);
    }

    private static void sortAscMap(List<Map<Object, Object>> list, String sortField) {
        Collections.sort(list, new Comparator<Map<Object, Object>>() {
            @Override
            public int compare(Map<Object, Object> map1, Map<Object, Object> map2) {
                return compareAsc((Number)map1.get(sortField), (Number)map2.get(sortField));
            }
        });
    }

    private static int compareDesc(Number number1, Number number2) {
        if (number1==null) return 1;
        if (number2==null) return -1;
        BigDecimal number1Value = new BigDecimal(number1.toString());
        BigDecimal number2Value = new BigDecimal(number2.toString());
        return number2Value.compareTo(number1Value);
    }

    private static <T> void sortDesc(List<T> list, String sortField) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return compareDesc((Number)BeanMap.create(o1).get(sortField), (Number)BeanMap.create(o2).get(sortField));
            }
        });
    }

    private static void sortDescMap(List<Map<Object, Object>> list, String sortField) {
        Collections.sort(list, new Comparator<Map<Object, Object>>() {
            @Override
            public int compare(Map<Object, Object> map1, Map<Object, Object> map2) {
                return compareDesc((Number)map1.get(sortField), (Number)map2.get(sortField));
            }
        });
    }
}