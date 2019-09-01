package com.hegp.entity;

import com.hegp.core.jpa.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role_resource_rel",
       indexes = {@Index(name="sys_role_resource_rel_role_id_index", columnList = "roleId", unique=false),
                  @Index(name="sys_role_resource_rel_resource_id_index", columnList="resourceId", unique=false)})
public class RoleResourceRelEntity extends IdEntity {
    private String roleId;
    private String resourceId;

    public RoleResourceRelEntity() { }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
