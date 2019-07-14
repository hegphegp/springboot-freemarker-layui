package com.hegp.repository;

import com.hegp.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupRepository extends JpaRepository<GroupEntity, String>, JpaSpecificationExecutor<GroupEntity> {
}
