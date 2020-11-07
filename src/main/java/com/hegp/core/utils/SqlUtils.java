package com.hegp.core.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SqlUtils {
    /**
     * 获取插入一个对象的SQL语句
     * @param tableName
     * @param fieldValues
     * @return
     */
    public static String getInsertOneSql(String tableName, Map<String, Object> fieldValues) {
        StringBuffer sb = new StringBuffer("INSERT INTO "+tableName+"(");
        StringBuffer values = new StringBuffer(" VALUES(");
        boolean isFirstField = true;
        for (String field:fieldValues.keySet()) {
            sb.append(isFirstField? field:","+field);
            values.append(isFirstField? ":"+field:", :"+field);
            if (isFirstField) {
                isFirstField=false;
            }
        }
        sb.append(")");
        values.append(");");
        return sb.toString()+values.toString();
    }

    public static String getInsertManySql(String tableName, List<Map<String,Object>> fieldValues) {
        return getInsertOneSql(tableName, fieldValues.get(0));
    }

    public static void main(String[] args) {
        String tableName = "sys_user";
        Map<String,Object> fieldValues = new HashMap();
        fieldValues.put("id", UUID.randomUUID().toString());
        fieldValues.put("del", true);
        String sql = getInsertOneSql(tableName, fieldValues);
        System.out.println(sql);
    }
}