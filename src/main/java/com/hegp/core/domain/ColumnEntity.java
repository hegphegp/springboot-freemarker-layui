package com.hegp.core.domain;

import com.hegp.core.domain.common.OrderEntity;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 必须要有主键, 且只能是name=id的列是主键, 其他不能是主键, 主键可以是数字类型, 字符串类型, 允许修改主键的类型, 改了把数据库抛错信息抛出来
 */
@Data
public class ColumnEntity extends OrderEntity {
    private static final Logger logger = LoggerFactory.getLogger(IndexColumnEntity.class);
    private Long id;
    private String name;
    private Integer length;
    private String type;
    private String defaultValue;
    private String description;
    private Long tableId;
}
