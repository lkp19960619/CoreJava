package com.baizhi.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 16:33
 * @Version 1.0
 */
@SpringBootApplication
//开启任务定时调度功能
@EnableScheduling
public class SpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
