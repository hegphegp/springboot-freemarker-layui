package com.hegp.core.domain.common;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * hibernate的雪花id生成器
 */
public class SnowflakeIdGenerator implements IdentifierGenerator {
    //参数1为终端ID
    //参数2为数据中心ID
    public Snowflake snowflake = IdUtil.createSnowflake(31, 31);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflake.nextId();
    }
}
