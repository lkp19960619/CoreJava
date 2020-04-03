package com.baizhi.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 13:14
 * @Version 1.0
 */
@SpringBootApplication
//开启定时任务调度功能
@EnableScheduling
public class SpringStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringStarter.class,args);
    }
}
