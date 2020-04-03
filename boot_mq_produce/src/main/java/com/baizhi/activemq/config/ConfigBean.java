package com.baizhi.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 13:33
 * @Version 1.0
 */
@Configuration
//开启JMS适配注解
@EnableJms
public class ConfigBean {
    @Value("${myqueue}")
    private String myQueue;

    @Bean //<Bean id="" class="">
    public Queue queue(){
        //根据指定的队列名返回一个队列
        return new ActiveMQQueue(myQueue);
    }
}
