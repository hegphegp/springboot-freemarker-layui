package com.hegp.repository;

import com.hegp.entity.UserRoleRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRoleRelRepository extends JpaRepository<UserRoleRelEntity, String>, JpaSpecificationExecutor<UserRoleRelEntity> {
}
