package com.hegp.core.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MySQLDialect {
    public final static Map<String, String> property2SqlColumnMap = new HashMap<>();
    public final static Map<String, Integer> lengthLimitColumnMap = new HashMap<>();
    public final static Set<String> columnAllTypes = property2SqlColumnMap.keySet();
    /**
     * 参考hibernate的方言 org.hibernate.dialect.MySQLDialect
     */
    static {
        property2SqlColumnMap.put("tinyint", "tinyint");
        property2SqlColumnMap.put("smallint", "smallint");
        property2SqlColumnMap.put("mediumint", "mediumint");
        property2SqlColumnMap.put("int", "int");
        property2SqlColumnMap.put("bigint", "bigint");
        property2SqlColumnMap.put("float", "float");
        property2SqlColumnMap.put("double", "double");
        property2SqlColumnMap.put("numeric", "decimal");
        property2SqlColumnMap.put("boolean", "bit");
        property2SqlColumnMap.put("date", "date");
        property2SqlColumnMap.put("time", "time");
        property2SqlColumnMap.put("timestamp", "datetime");
        property2SqlColumnMap.put("char", "char(1)");            // char无论设置了多长, 长度都为1
        property2SqlColumnMap.put("string", "varchar");          // 最大长度65535
        property2SqlColumnMap.put("tinytext", "tinytext");
        property2SqlColumnMap.put("text", "text");
        property2SqlColumnMap.put("mediumtext", "mediumtext");
        property2SqlColumnMap.put("longtext", "longtext");

        lengthLimitColumnMap.put("string", 65535);
        lengthLimitColumnMap.put("numeric", 65);

    }

    public static void main(String[] args) {
        System.out.println(columnAllTypes);
    }
}
