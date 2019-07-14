package com.hegp.entity;

import com.hegp.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_group_type",
       indexes = {@Index(name="sys_group_type_name_index", columnList = "name", unique=false)})
public class GroupTypeEntity extends BaseEntity {

}
