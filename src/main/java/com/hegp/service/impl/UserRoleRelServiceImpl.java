package com.hegp.service.impl;

import com.hegp.core.jpa.service.impl.JPAServiceImpl;
import com.hegp.entity.UserRoleRelEntity;
import com.hegp.service.UserRoleRelService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleRelServiceImpl extends JPAServiceImpl<UserRoleRelEntity, String> implements UserRoleRelService {
}