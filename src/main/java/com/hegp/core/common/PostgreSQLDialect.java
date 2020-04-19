package com.hegp.core.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * postgresql
 */
public class PostgreSQLDialect {
    public final static Map<String, String> property2SqlColumnMap = new HashMap<>();
    public final static Map<String, Integer> lengthLimitColumnMap = new HashMap<>();
    public final static Set<String> columnAllTypes = property2SqlColumnMap.keySet();
    public final static Integer NUMERIC_M_MAX_LENGTH = 1000;  // numeric(M,N); M最大值是1000，N最大值是1000
    public final static Integer NUMERIC_N_MAX_LENGTH = 1000;  // numeric(M,N); M最大值是1000，N最大值是1000
    // 因为可视化界面, mysql, postgresql, oracle的数据建模界面只走一套界面, 不单独区分各种数据库差异的各种属性类型, 没精力陪他们数据库厂商玩个性化属性类型
    /**
     * 参考hibernate的方言 org.hibernate.dialect.MySQLDialect
     */
    static {
        property2SqlColumnMap.put("int", "int4");
        property2SqlColumnMap.put("long", "int8");
        property2SqlColumnMap.put("float", "float4");
        property2SqlColumnMap.put("double", "float8");
        property2SqlColumnMap.put("numeric", "numeric");
        property2SqlColumnMap.put("boolean", "bool");
        property2SqlColumnMap.put("date", "date");
        property2SqlColumnMap.put("timestamp", "datetime");
        property2SqlColumnMap.put("varchar", "varchar");          // 最大长度65535
        property2SqlColumnMap.put("tinytext", "text");
        property2SqlColumnMap.put("text", "text");
        property2SqlColumnMap.put("mediumtext", "text");
        property2SqlColumnMap.put("longtext", "text");
        property2SqlColumnMap.put("char", "char");

        lengthLimitColumnMap.put("varchar", 65535);

    }

    public static void main(String[] args) {
        System.out.println(columnAllTypes);
    }
}
