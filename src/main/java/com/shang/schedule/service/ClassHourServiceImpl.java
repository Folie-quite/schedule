package com.shang.schedule.service;

import com.shang.schedule.mapper.ClassHourMapper;
import com.shang.schedule.pojo.ClassHour;
import com.shang.schedule.pojo.ClassHourExample;
import com.shang.schedule.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：Shang
 * @date ：Created at 0009 2022/2/9 17:10
 * @description：
 * @version:
 */
@Service
public class ClassHourServiceImpl implements ClassHourService{

    @Autowired
    private ClassHourMapper classHourMapper;

    @Override
    public MyResult addClassHour(ClassHour classHour) {
        if(null == classHour.getCreateDate())
            classHour.setCreateDate(new Date());
        classHourMapper.insert(classHour);
        return MyResult.ok();
    }

    @Override
    public MyResult addClassHourMath(ClassHour classHour, Integer hour, Integer type) {
        ClassHour classHourByStatus = this.getClassHourByStatus(classHour.getCreateName());

        classHour.setStatus(1);
        classHour.setType(type);
        classHour.setVariation(hour);

        if(null != classHourByStatus){
            classHourByStatus.setStatus(0);
            classHour.setBalance(classHourByStatus.getBalance() + hour);
            classHourByStatus.setUpdateName(classHour.getCreateName());
            this.updateClassHourByKey(classHourByStatus);
        } else {
            classHour.setBalance(hour);
        }

        return this.addClassHour(classHour);
    }

    @Override
    public ClassHour getClassHourByStatus(String userName) {
        ClassHour classHour = new ClassHour();
        classHour.setCreateName(userName);
        classHour.setStatus(1);
        MyResult classHourByExample = this.getClassHourByExample(classHour);
        List<ClassHour> classhours = (List<ClassHour>)classHourByExample.getData();
        if(null != classhours && classhours.size() > 0){
            classHour = classhours.get(0);
        } else {
            classHour = null;
        }
        return classHour;
    }

    @Override
    public MyResult getClassHourByNoStatus(String userName) {

        return null;
    }

    @Override
    public MyResult getClassHourByExample(ClassHour classHour) {
        ClassHourExample example = new ClassHourExample();
        ClassHourExample.Criteria criteria = example.createCriteria();

        if(null != classHour.getCreateName())
            criteria.andCreateNameEqualTo(classHour.getCreateName());
        if(null != classHour.getId())
            criteria.andIdEqualTo(classHour.getId());
        if(null != classHour.getStatus())
            criteria.andStatusEqualTo(classHour.getStatus());

        List<ClassHour> classHours = classHourMapper.selectByExample(example);

        return MyResult.ok(classHours);
    }

    @Override
    public MyResult updateClassHourByKey(ClassHour classHour) {
        if(null == classHour.getUpdateDate())
            classHour.setUpdateDate(new Date());
        classHourMapper.updateByPrimaryKeySelective(classHour);
        return MyResult.ok();
    }
}
