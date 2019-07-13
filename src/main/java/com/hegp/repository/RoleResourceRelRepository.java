package com.hegp.repository;

import com.hegp.entity.RoleEntity;
import com.hegp.entity.RoleResourceRelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleResourceRelRepository extends JpaRepository<RoleResourceRelEntity, String>, JpaSpecificationExecutor<RoleResourceRelEntity> {
}
