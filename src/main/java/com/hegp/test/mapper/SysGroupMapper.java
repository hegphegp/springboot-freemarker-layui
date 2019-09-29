package com.hegp.test.mapper;

import com.hegp.test.domain.SysGroup;
import com.hegp.test.example.SysGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysGroupMapper {
    long countByExample(SysGroupExample example);

    int deleteByExample(SysGroupExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    List<SysGroup> selectByExample(SysGroupExample example);

    SysGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysGroup record, @Param("example") SysGroupExample example);

    int updateByExample(@Param("record") SysGroup record, @Param("example") SysGroupExample example);

    int updateByPrimaryKeySelective(SysGroup record);

    int updateByPrimaryKey(SysGroup record);
}