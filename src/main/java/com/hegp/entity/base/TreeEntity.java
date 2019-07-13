package com.hegp.entity.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TreeEntity extends BaseEntity {
    private String parentId;

    public TreeEntity() { }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
