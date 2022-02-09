package com.shang.schedule.service;

import com.shang.schedule.pojo.ClassHour;
import com.shang.schedule.utils.MyResult;

import java.util.List;

/**
 * @author ：Shang
 * @date ：Created at 0009 2022/2/9 17:10
 * @description：
 * @version:
 */

public interface ClassHourService {

    /**
     * 新增数据
     * @param classHour
     * @return
     */
    MyResult addClassHour(ClassHour classHour);

    /**
     * 带计算的新增数据
     * @param classHour
     * @param hour
     * @param type 动作类型，1充值，2消费
     * @return
     */
    MyResult addClassHourMath(ClassHour classHour, Integer hour, Integer type);

    /**
     * 通过用户名查询当前活跃的唯一信息。
     * @param userName
     * @return
     */
    ClassHour getClassHourByStatus(String userName);

    /**
     * 查询不活跃列表
     * @param userName
     * @return
     */
    MyResult getClassHourByNoStatus(String userName);

    /**
     * 按条件查询
     * @param classHour
     * @return
     */
    MyResult getClassHourByExample(ClassHour classHour);

    /**
     * 根据主键更新数据
     * @param classHour
     * @return
     */
    MyResult updateClassHourByKey(ClassHour classHour);

}
