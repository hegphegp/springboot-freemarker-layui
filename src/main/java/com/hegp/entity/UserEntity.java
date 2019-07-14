package com.hegp.entity;

import com.hegp.entity.base.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user",
       indexes = {@Index(name="sys_user_username_index", columnList = "username", unique=false),
                  @Index(name="sys_user_del_index", columnList = "del", unique=false),
                  @Index(name="sys_user_create_at_index", columnList = "createAt", unique=false),
                  @Index(name="sys_user_update_at_index", columnList = "updateAt", unique=false)})
public class UserEntity extends IdEntity {
    private String username;
    private String nickname;
    private String phone;
    private Boolean del;

    public UserEntity() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
