package com.hegp.entity;

import com.hegp.core.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_role_rel",
       indexes = {@Index(name="sys_user_role_rel_user_id_index", columnList = "userId", unique=false),
                  @Index(name="sys_user_role_rel_role_id_index", columnList = "roleId", unique=false)})
public class UserRoleRelEntity extends BaseEntity {
    private String userId;
    private String roleId;

    public UserRoleRelEntity() { }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
