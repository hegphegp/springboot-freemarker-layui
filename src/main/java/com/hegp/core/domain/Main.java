package com.hegp.core.domain;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hgp
 * @date 20-5-8
 */
public class Main {
    public static void main(String[] args) {
        Snowflake snowflake = IdUtil.createSnowflake(31, 31);
        List<TableEntity> tables = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Long tableId = snowflake.nextId();
            TableEntity table = new TableEntity();
            table.setId(tableId);
            table.setDescription("描述");
            table.setSync(true);
            table.setType("表");
            List<ColumnEntity> columns = new ArrayList<>();

            ColumnEntity id = new ColumnEntity();
            Long idId = snowflake.nextId();
            id.setId(idId);
            id.setTableId(tableId);
            id.setName("id");
            id.setDefaultValue("1");
            id.setLength(32);
            id.setType("varchar");
            columns.add(id);


            ColumnEntity name = new ColumnEntity();
            Long nameId = snowflake.nextId();
            name.setId(nameId);
            name.setTableId(tableId);
            name.setName("name");
            name.setDefaultValue("name");
            name.setLength(32);
            name.setType("varchar");
            columns.add(name);


            ColumnEntity age = new ColumnEntity();
            Long ageId = snowflake.nextId();
            age.setId(ageId);
            age.setTableId(tableId);
            age.setName("age");
            age.setDefaultValue("1");
            age.setLength(11);
            name.setType("int");
            columns.add(name);


            System.out.println("===");
        }
    }
}
