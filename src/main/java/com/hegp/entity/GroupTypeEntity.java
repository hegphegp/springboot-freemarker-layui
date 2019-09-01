package com.hegp.entity;

import com.hegp.core.jpa.entity.TreeEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_group_type",
       indexes = {@Index(name="sys_group_type_name_index", columnList = "name", unique=false),
                  @Index(name="sys_group_type_parent_id_index", columnList = "parentId", unique=false),
                  @Index(name="sys_group_type_order_index_index", columnList = "orderIndex", unique=false)})
public class GroupTypeEntity extends TreeEntity {

}
