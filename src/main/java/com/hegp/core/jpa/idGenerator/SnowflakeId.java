package com.hegp.core.jpa.idGenerator;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class SnowflakeId implements IdentifierGenerator {
    //参数1为终端ID
    //参数2为数据中心ID
    private Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflake.nextId();
    }
}
