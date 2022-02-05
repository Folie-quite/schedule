package com.shang.schedule;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shang.schedule.mapper")
@SpringBootApplication
public class ScheduleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
        LOGGER.error("ヾ(◍°∇°◍)ﾉﾞ    系统服务端启动成功      ヾ(◍°∇°◍)ﾉﾞ");
    }

}
