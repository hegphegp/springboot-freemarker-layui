package com.hegp.service.impl;

import com.hegp.core.jpa.service.impl.JPAServiceImpl;
import com.hegp.entity.ResourceEntity;
import com.hegp.service.ResourceService;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends JPAServiceImpl<ResourceEntity, String> implements ResourceService {
}
