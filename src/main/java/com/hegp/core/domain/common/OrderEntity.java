package com.hegp.core.domain.common;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hgp
 * @date 20-5-8
 */
@Data
public class OrderEntity implements Comparable<OrderEntity> {
    private static final Logger logger = LoggerFactory.getLogger(OrderEntity.class);
    private Integer orderNo; // 排序号

    @Override
    public int compareTo(OrderEntity o) {
        if (o==null || o.orderNo==null) {
            logger.error("\n\n\t\t发现脏数据, 数据建模的索引按照字段排序时, 发现比较的对象是null, 数据库可能有脏数据, 请检查一下"+o.getClass()+"对应的数据表名\n\n");
            return 1;
        }
        if (this.orderNo==null) {
            logger.error("\n\n\t\t发现脏数据, 数据建模的索引按照字段排序时, 发现比较的对象是null, 数据库可能有脏数据, 请检查一下"+o.getClass()+"数据表名\n\n");
            return -1;
        }
        return this.orderNo - o.orderNo;
    }
}
