package com.hegp.core.domain;

import lombok.Data;

/**
 * 1) 一个数据库有多个表, 表有排序 (不做)
 * 2) 一个表有多个列, 列有排序
 * 3) 一个有多个索引, 索引有排序
 * 4) 一个索引有多个字段, 字段有排序
 */
@Data
public class TableEntity {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Boolean sync;        // 是否同步
}
