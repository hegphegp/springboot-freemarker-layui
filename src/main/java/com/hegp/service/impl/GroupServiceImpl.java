package com.hegp.service.impl;

import com.hegp.core.jpa.service.impl.JPAServiceImpl;
import com.hegp.entity.GroupEntity;
import com.hegp.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends JPAServiceImpl<GroupEntity, String> implements GroupService {
}
