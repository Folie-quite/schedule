package com.shang.schedule.controller;

import com.shang.schedule.configuration.SystemLogsAOP;
import com.shang.schedule.jedis.JedisService;
import com.shang.schedule.pojo.ClassHour;
import com.shang.schedule.pojo.Users;
import com.shang.schedule.service.ClassHourService;
import com.shang.schedule.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Shang
 * @date ：Created at 0009 2022/2/9 17:13
 * @description：
 * @version:
 */
@RestController
public class ClassHourController {

    @Autowired
    private ClassHourService classHourService;
    @Autowired
    private JedisService jedisService;

    @PostMapping("/addClassHour")
    public MyResult addClassHour(ClassHour classHour, Integer hour, String userId){
        Users user = (Users)jedisService.get(userId);
        classHour.setCreateName(user.getUserName());
        classHourService.addClassHourMath(classHour, hour, 1);
        return null;
    }

    @RequestMapping("/getClassHour")
    @SystemLogsAOP
    public MyResult getClassHour(String userId){
        Users user = (Users)jedisService.get(userId);
        ClassHour classHourByStatus = classHourService.getClassHourByStatus(user.getUserName());
        return MyResult.ok(classHourByStatus.getBalance());
    }

    @RequestMapping("/getClassHours")
    public MyResult getClassHours(String userId){
        Users user = (Users)jedisService.get(userId);
        ClassHour classHourByStatus = classHourService.getClassHourByStatus(user.getUserName());
        return MyResult.ok(classHourByStatus.getBalance());
    }



}
