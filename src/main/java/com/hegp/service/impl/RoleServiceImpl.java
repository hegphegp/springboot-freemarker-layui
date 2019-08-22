package com.hegp.service.impl;

import com.hegp.core.jpa.service.impl.JPAServiceImpl;
import com.hegp.entity.RoleEntity;
import com.hegp.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends JPAServiceImpl<RoleEntity, String> implements RoleService {
}
