package com.shang.schedule.mapper;

import com.shang.schedule.pojo.SystemLogs;
import com.shang.schedule.pojo.SystemLogsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemLogsMapper {
    int countByExample(SystemLogsExample example);

    int deleteByExample(SystemLogsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemLogs record);

    int insertSelective(SystemLogs record);

    List<SystemLogs> selectByExampleWithBLOBs(SystemLogsExample example);

    List<SystemLogs> selectByExample(SystemLogsExample example);

    SystemLogs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemLogs record, @Param("example") SystemLogsExample example);

    int updateByExampleWithBLOBs(@Param("record") SystemLogs record, @Param("example") SystemLogsExample example);

    int updateByExample(@Param("record") SystemLogs record, @Param("example") SystemLogsExample example);

    int updateByPrimaryKeySelective(SystemLogs record);

    int updateByPrimaryKeyWithBLOBs(SystemLogs record);

    int updateByPrimaryKey(SystemLogs record);
}