package com.shang.schedule.mapper;

import com.shang.schedule.pojo.StudyTime;
import com.shang.schedule.pojo.StudyTimeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyTimeMapper {
    int countByExample(StudyTimeExample example);

    int deleteByExample(StudyTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudyTime record);

    int insertSelective(StudyTime record);

    List<StudyTime> selectByExample(StudyTimeExample example);

    StudyTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudyTime record, @Param("example") StudyTimeExample example);

    int updateByExample(@Param("record") StudyTime record, @Param("example") StudyTimeExample example);

    int updateByPrimaryKeySelective(StudyTime record);

    int updateByPrimaryKey(StudyTime record);
}