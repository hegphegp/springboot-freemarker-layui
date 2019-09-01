package com.hegp.core.jpa.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity extends IdEntity {
    private String name;

    public BaseEntity() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
