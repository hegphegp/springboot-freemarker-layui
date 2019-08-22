package com.hegp.service.impl;

import com.hegp.core.jpa.service.impl.JPAServiceImpl;
import com.hegp.entity.RoleResourceRelEntity;
import com.hegp.service.RoleResourceRelService;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceRelServiceImpl extends JPAServiceImpl<RoleResourceRelEntity, String> implements RoleResourceRelService {
}
