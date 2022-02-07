package com.shang.schedule.mapper;

import com.shang.schedule.pojo.ClassHour;
import com.shang.schedule.pojo.ClassHourExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassHourMapper {
    int countByExample(ClassHourExample example);

    int deleteByExample(ClassHourExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassHour record);

    int insertSelective(ClassHour record);

    List<ClassHour> selectByExample(ClassHourExample example);

    ClassHour selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassHour record, @Param("example") ClassHourExample example);

    int updateByExample(@Param("record") ClassHour record, @Param("example") ClassHourExample example);

    int updateByPrimaryKeySelective(ClassHour record);

    int updateByPrimaryKey(ClassHour record);
}