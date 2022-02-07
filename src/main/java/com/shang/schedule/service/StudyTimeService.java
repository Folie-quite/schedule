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

    MyResult addStudyTime(StudyTime studyTime);
}
