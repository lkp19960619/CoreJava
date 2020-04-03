package com.baizhi.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 12:14
 * @Version 1.0
 */
@Service
public class SpringMQ_Produce {
    //自动注入jmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) {
        //加载配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-activemq.xml");
        //构建生产者,通过Spring，相当于new对象
        SpringMQ_Produce produce = (SpringMQ_Produce) applicationContext.getBean("springMQ_Produce");
        produce.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("Spring和ActiveMQ整合之后的案例");
                return textMessage;
            }
        });
        System.out.println("send task over");
    }
}
