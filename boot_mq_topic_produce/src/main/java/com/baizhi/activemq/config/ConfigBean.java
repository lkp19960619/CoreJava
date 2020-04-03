package com.baizhi.activemq.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 16:28
 * @Version 1.0
 */
@Configuration
public class ConfigBean {
    @Value("${myTopic}")
    private String myTopic;

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(myTopic);
    }
}
