package com.hegp.service.impl;

import com.hegp.core.jpa.service.impl.JPAServiceImpl;
import com.hegp.entity.UserEntity;
import com.hegp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends JPAServiceImpl<UserEntity, String> implements UserService {
}
