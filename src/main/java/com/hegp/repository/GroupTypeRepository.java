package com.hegp.repository;

import com.hegp.entity.GroupTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupTypeRepository extends JpaRepository<GroupTypeEntity, String>, JpaSpecificationExecutor<GroupTypeEntity> {
}
