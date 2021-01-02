package com.hegp.core.utils.sql;

import java.util.*;
import java.util.regex.Pattern;

public class SqlUtils {
    public final static String regEx = "[a-zA-Z]{1,}[a-zA-Z0-9_-]{0,}";

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

    public static boolean checkColumnName(String columnName) {
        return (Pattern.matches(regEx, columnName));
    }

    public static void main(String[] args) {
        String tableName = "sys_user";
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Map<String,Object> fieldValues = new HashMap();
            fieldValues.put("id", UUID.randomUUID().toString());
            fieldValues.put("del", true);
            String sql = getInsertOneSql(tableName, fieldValues);
            System.out.println(sql);
            list.add(fieldValues);
        }
        String sql = getInsertManySql(tableName, list);
        System.out.println(sql);
    }
}