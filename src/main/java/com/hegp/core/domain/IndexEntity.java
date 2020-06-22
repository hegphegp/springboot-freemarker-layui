package com.hegp.core.domain;

import com.hegp.core.domain.common.OrderEntity;
import lombok.Data;

/**
 * @author hgp
 * @date 20-5-8
 */
@Data
public class IndexEntity extends OrderEntity {
    private Long id;
    private String name;
    private Long tableId;
}
