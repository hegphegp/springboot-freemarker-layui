package com.hegp.core.jpa.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TreeEntity extends BaseEntity {
    private String parentId;
    private Long orderIndex;

    public TreeEntity() { }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }
}
