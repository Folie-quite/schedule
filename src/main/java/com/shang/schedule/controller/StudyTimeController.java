package com.shang.schedule.controller;

import com.shang.schedule.pojo.StudyTime;
import com.shang.schedule.service.StudyTimeService;
import com.shang.schedule.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Shang
 * @date ：Created at 0007 2022/2/7 22:30
 * @description：
 * @version:
 */
@RestController
public class StudyTimeController {

    @Autowired
    private StudyTimeService studyTimeService;

    @PostMapping("/addStudyTime")
    public MyResult addStudyTime(StudyTime studyTime, String index){
        System.out.println(index);
        MyResult myResult = studyTimeService.addStudyTime(studyTime);
        return myResult;
    }


}
