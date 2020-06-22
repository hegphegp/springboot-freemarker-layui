package com.hegp.core.domain;

import com.hegp.core.domain.common.OrderEntity;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hgp
 * @date 20-5-8
 */
@Data
public class IndexColumnEntity extends OrderEntity {
    private static final Logger logger = LoggerFactory.getLogger(IndexColumnEntity.class);
    private Long id;
    private Long columnId;  // 列名ID
    private Long indexId;   // 索引ID
}
