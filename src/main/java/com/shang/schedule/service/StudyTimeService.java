package com.shang.schedule.service;

import com.shang.schedule.pojo.StudyTime;
import com.shang.schedule.utils.MyResult;

/**
 * @author ：Shang
 * @date ：Created at 0007 2022/2/7 23:45
 * @description：
 * @version:
 */
public interface StudyTimeService {

    /**
     * 新增数据
     * @param studyTime
     * @return
     */
    MyResult addStudyTime(StudyTime studyTime);
    MyResult addStudyTime(StudyTime studyTime, String index);

    /**
     * 根据条件查询
     * @param studyTime
     * @return
     */
    MyResult getStudyTimeByExample(StudyTime studyTime);
}
