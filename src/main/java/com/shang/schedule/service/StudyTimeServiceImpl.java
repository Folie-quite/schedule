package com.shang.schedule.service;

import com.shang.schedule.mapper.StudyTimeMapper;
import com.shang.schedule.pojo.StudyTime;
import com.shang.schedule.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Shang
 * @date ：Created at 0007 2022/2/7 23:45
 * @description：
 * @version:
 */

@Service
public class StudyTimeServiceImpl implements StudyTimeService{

    @Autowired
    private StudyTimeMapper studyTimeMapper;

    @Override
    public MyResult addStudyTime(StudyTime studyTime) {
        studyTimeMapper.insert(studyTime);
        return MyResult.ok();
    }
}
