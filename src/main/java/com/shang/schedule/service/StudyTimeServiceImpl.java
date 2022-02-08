package com.shang.schedule.service;

import com.shang.schedule.mapper.StudyTimeMapper;
import com.shang.schedule.pojo.StudyTime;
import com.shang.schedule.utils.MyDateUtil;
import com.shang.schedule.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        if(null == studyTime.getCreateDate()){
            studyTime.setCreateDate(new Date());
        }
        studyTimeMapper.insert(studyTime);
        return MyResult.ok();
    }

    @Override
    public MyResult addStudyTime(StudyTime studyTime, String index) {

        Date date = MyDateUtil.parseStrToDate(index, MyDateUtil.DATE_FORMAT_YYYY_MM_DD);
        studyTime.setYear(MyDateUtil.getYear(date));
        studyTime.setMonth(MyDateUtil.getMonth(date));
        studyTime.setDay(MyDateUtil.getDay(date));
        return this.addStudyTime(studyTime);
    }

    @Override
    public MyResult getStudyTimeByExample(StudyTime studyTime) {
        return null;
    }
}
