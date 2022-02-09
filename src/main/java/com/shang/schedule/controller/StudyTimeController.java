package com.shang.schedule.controller;

import com.shang.schedule.jedis.JedisService;
import com.shang.schedule.pojo.ClassHour;
import com.shang.schedule.pojo.StudyTime;
import com.shang.schedule.pojo.Users;
import com.shang.schedule.service.ClassHourService;
import com.shang.schedule.service.StudyTimeService;
import com.shang.schedule.utils.MyResult;
import com.shang.schedule.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private ClassHourService classHourService;
    @Autowired
    private JedisService jedisService;

    @PostMapping("/addStudyTime")
    public MyResult addStudyTime(StudyTime studyTime, String index, String userId){
        Users user = (Users)jedisService.get(userId);
        studyTime.setStudent(user.getUserName());
        studyTime.setCreateUser(user.getUserName());
        MyResult myResult = studyTimeService.addStudyTime(studyTime, index);
        ClassHour classHour = new ClassHour(user.getUserName(), studyTime.getTeacher());
        classHourService.addClassHourMath(classHour, studyTime.getHour(), 2);
        return myResult;
    }

    @RequestMapping("/getStudyTimes")
    public MyResult getStudyTimes(StudyTime studyTime, String userId){
        Users user = (Users)jedisService.get(userId);
        studyTime.setStudent(user.getUserName());
        MyResult studyTimeByExample = studyTimeService.getStudyTimeByExample(studyTime);
        return studyTimeByExample;
    }



}
