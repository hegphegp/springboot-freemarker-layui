package com.hegp.entity;

import com.hegp.core.jpa.entity.TreeEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_resource",
       indexes = {@Index(name="sys_resource_name_index", columnList = "name", unique=false),
                  @Index(name="sys_resource_parent_id_index", columnList = "parentId", unique=false),
                  @Index(name="sys_resource_order_index_index", columnList = "orderIndex", unique=false)})
public class ResourceEntity extends TreeEntity {
    public ResourceEntity() { }
}
