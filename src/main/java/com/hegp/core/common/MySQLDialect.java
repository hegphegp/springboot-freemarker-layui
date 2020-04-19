package com.hegp.core.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * navicat查看numeric(M,N)大型数字时，会显示科学计数法，导致在界面编辑数据时，科学计数法的数据全部改错
 * phpmyadmin查看numeric(M,N)大型数字时，显示正确，修改也正确
 * MySQLDialect, MariaDBDialect共用这个类
 */
public class MySQLDialect {
    public final static Map<String, String> property2SqlColumnMap = new HashMap<>();
    public final static Map<String, Integer> lengthLimitColumnMap = new HashMap<>();
    public final static Set<String> columnAllTypes = property2SqlColumnMap.keySet();
    public final static Integer NUMERIC_M_MAX_LENGTH = 65;  // numeric(M,N); M最大值是65，N最大值是30
    public final static Integer NUMERIC_N_MAX_LENGTH = 30;  // numeric(M,N); M最大值是65，N最大值是30
    /**
     * 参考hibernate的方言 org.hibernate.dialect.MySQLDialect
     */
    static {
        property2SqlColumnMap.put("int", "int");
        property2SqlColumnMap.put("long", "bigint");
        property2SqlColumnMap.put("float", "float");
        property2SqlColumnMap.put("double", "double");
        property2SqlColumnMap.put("numeric", "numeric");
        property2SqlColumnMap.put("boolean", "bit");
        property2SqlColumnMap.put("date", "date");
        property2SqlColumnMap.put("timestamp", "datetime");
        property2SqlColumnMap.put("char", "char");                // char无论设置了多长, 长度都为1
        property2SqlColumnMap.put("varchar", "varchar");          // 最大长度65535
        property2SqlColumnMap.put("tinytext", "tinytext");
        property2SqlColumnMap.put("text", "text");
        property2SqlColumnMap.put("mediumtext", "mediumtext");
        property2SqlColumnMap.put("longtext", "longtext");

        lengthLimitColumnMap.put("string", 65535);

    }

    public static void main(String[] args) {
        System.out.println(columnAllTypes);
    }
}
