package com.hegp.entity;

import com.hegp.core.jpa.entity.TreeEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_group",
       indexes = {@Index(name="sys_group_name_index", columnList = "name", unique=false),
                  @Index(name="sys_group_level_index", columnList = "level", unique=false),
                  @Index(name="sys_group_parent_id_index", columnList = "parentId", unique=false),
                  @Index(name="sys_group_order_index_index", columnList = "orderIndex", unique=false),
                  @Index(name="sys_group_group_type_id_index", columnList = "groupTypeId", unique=false)})
public class GroupEntity extends TreeEntity {
    private String groupTypeId;
    private Integer level;

    public GroupEntity() { }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(String groupTypeId) {
        this.groupTypeId = groupTypeId;
    }
}
