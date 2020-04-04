package com.hegp.core.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 现阶段只兼容MySQL和postgresql, 没了解Oracle的数据类型, 暂不定义
 */
public class PostgreSQLDialect {
    public final static Map<String, String> property2SqlColumnMap = new HashMap<>();
    public final static Map<String, Integer> lengthLimitColumnMap = new HashMap<>();
    public final static Set<String> columnAllTypes = property2SqlColumnMap.keySet();
    // 因为可视化界面, mysql, postgresql, oracle的数据建模界面只走一套界面, 不单独区分各种数据库差异的各种属性类型, 没精力陪他们数据库厂商玩个性化属性类型
    /**
     * 参考hibernate的方言 org.hibernate.dialect.MySQLDialect
     */
    static {
        property2SqlColumnMap.put("tinyint", "int2");
        property2SqlColumnMap.put("smallint", "int2");
        property2SqlColumnMap.put("mediumint", "mediumint");
        property2SqlColumnMap.put("int", "int4");
        property2SqlColumnMap.put("bigint", "int8");
        property2SqlColumnMap.put("float", "float4");
        property2SqlColumnMap.put("double", "float8");
        property2SqlColumnMap.put("numeric", "decimal");
        property2SqlColumnMap.put("boolean", "bool");
        property2SqlColumnMap.put("date", "date");
        property2SqlColumnMap.put("time", "time");
        property2SqlColumnMap.put("timestamp", "datetime");
        property2SqlColumnMap.put("char", "char(1)");            // char无论设置了多长, 长度都为1
        property2SqlColumnMap.put("string", "varchar");          // 最大长度65535
        property2SqlColumnMap.put("text", "text");

        lengthLimitColumnMap.put("string", 65535);
        lengthLimitColumnMap.put("numeric", 65);


//        this.registerColumnType(-7, "bool");
//        this.registerColumnType(-5, "int8");
//        this.registerColumnType(5, "int2");
//        this.registerColumnType(-6, "int2");
//        this.registerColumnType(4, "int4");
//        this.registerColumnType(1, "char(1)");
//        this.registerColumnType(12, "varchar($l)");
//        this.registerColumnType(6, "float4");
//        this.registerColumnType(8, "float8");
//        this.registerColumnType(91, "date");
//        this.registerColumnType(92, "time");
//        this.registerColumnType(93, "timestamp");
//        this.registerColumnType(-3, "bytea");
//        this.registerColumnType(-2, "bytea");
//        this.registerColumnType(-1, "text");
//        this.registerColumnType(-4, "bytea");
//        this.registerColumnType(2005, "text");
//        this.registerColumnType(2004, "oid");
//        this.registerColumnType(2, "numeric($p, $s)");
//        this.registerColumnType(1111, "uuid");

    }

    public static void main(String[] args) {
        System.out.println(columnAllTypes);
    }
}
