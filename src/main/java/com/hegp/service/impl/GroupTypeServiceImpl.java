package com.hegp.service.impl;

import com.hegp.core.jpa.service.impl.JPAServiceImpl;
import com.hegp.entity.GroupTypeEntity;
import com.hegp.service.GroupTypeService;
import org.springframework.stereotype.Service;

@Service
public class GroupTypeServiceImpl extends JPAServiceImpl<GroupTypeEntity, String> implements GroupTypeService {
}
