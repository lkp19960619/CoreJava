package com.baizhi.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 12:23
 * @Version 1.0
 */
@Service
public class SpringMQ_Consume {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-activemq.xml");
        SpringMQ_Consume consume = applicationContext.getBean(SpringMQ_Consume.class);
        String retValue = (String) consume.jmsTemplate.receiveAndConvert();
        System.out.println("***消费者收到的消息："+retValue);
    }
}
