package com.hegp.entity;

import com.hegp.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_resource",
       indexes = {@Index(name="sys_resource_name_index", columnList = "name", unique=false)})
public class ResourceEntity extends BaseEntity {
    public ResourceEntity() { }
}
