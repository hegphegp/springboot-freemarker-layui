package com.hegp.entity;

import com.hegp.core.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role",
       indexes = {@Index(name="sys_role_name_index", columnList = "name", unique=false)})
public class RoleEntity extends BaseEntity {
    public RoleEntity() { }
}
