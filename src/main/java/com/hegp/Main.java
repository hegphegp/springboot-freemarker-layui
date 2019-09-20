package com.hegp;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;

public class Main {

    /**
     * [
     *     {
     *         "name":"甘肃省",
     *         "pid":0,
     *         "id":1
     *     }, {
     *         "name":"天水市",
     *         "pid":1,
     *         "id":2
     *     }, {
     *         "name":"秦州区",
     *         "pid":2,
     *         "id":3
     *     }, {
     *         "name":"北京市",
     *         "pid":0,
     *         "id":4
     *     }, {
     *         "name":"昌平区",
     *         "pid":4,
     *         "id":5
     *     }
     * ]
     * @param args
     */
    public static void main(String[] args) {
        List<Example> list = new ArrayList<>();
        list.add(new Example(1,0, "甘肃省", 1));
        list.add(new Example(2,1, "天水市", 2));
        list.add(new Example(3,2, "秦州区", 3));
        list.add(new Example(4,0, "北京市", 4));
        list.add(new Example(5,4, "昌平区", 5));
        List newList = list2TreeListAsc(list, "id", "pid", "children", "sort");
        System.out.println(JSON.toJSONString(newList));

        list = new ArrayList<>();
        list.add(new Example(5,4, "昌平区", 5));
        list.add(new Example(4,0, "北京市", 4));
        list.add(new Example(3,2, "秦州区", 3));
        list.add(new Example(2,1, "天水市", 2));
        list.add(new Example(1,0, "甘肃省", 1));

        newList = list2TreeListAsc(list, "id", "pid", "children", "sort");
        System.out.println(JSON.toJSONString(newList));
    }

    public static <T> List<T> list2TreeListAsc(List<T> list, String idField, String parentIdField, String childrenField, String sortField) {
        // 非空校验,冗余的垃圾校验代码
        if (ObjectUtils.isEmpty(list)) return list;
        list.remove(null);
        if (ObjectUtils.isEmpty(list)) return list;

        List<T> root = new ArrayList<>();
        Map<Object, T> map = new HashMap<>();
        for (T t:list) {
            map.put(BeanMap.create(t).get(idField), t);
        }
        Map<Object, List<T>> childrenMap = new HashMap<>();
        for (T t:list) {
            BeanMap beanMap = BeanMap.create(t);
            T parent = map.get(beanMap.get(parentIdField));
            if (parent==null) {
                root.add(t);
            } else {
                List<T> children = childrenMap.get(beanMap.get(parentIdField));
                if (ObjectUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                    childrenMap.put(beanMap.get(parentIdField), children);
                }
                children.add(t);
                BeanMap.create(parent).put(childrenField, children);
            }
        }

        for (Object key:childrenMap.keySet()) {
            sortAsc(childrenMap.get(key), sortField);
        }
        sortAsc(root, sortField);
        return root;
    }

    public static  <T> void sortAsc(List<T> list, String sortField) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Number o1SortField = (Number)BeanMap.create(o1).get(sortField);
                Number o2SortField = (Number)BeanMap.create(o2).get(sortField);
                if (o1SortField==null) return -1;
                if (o2SortField==null) return 1;
                BigDecimal o1FieldValue = new BigDecimal(o1SortField.toString());
                BigDecimal o2FieldValue = new BigDecimal(o2SortField.toString());
                return o1FieldValue.compareTo(o2FieldValue);
            }
        });
    }
}

class Example {
    private int id;
    private int pid;
    private int sort;
    private String name;
    private List<Example> children;

    public Example() {}

    public Example(int id, int pid, String name, int sort) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.sort = sort;
    }

    public Example(int id, int pid) {
        this.id = id;
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Example> getChildren() {
        return children;
    }

    public void setChildren(List<Example> children) {
        this.children = children;
    }
}