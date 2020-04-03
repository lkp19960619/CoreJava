package com.baizhi.activemq.consume;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 16:41
 * @Version 1.0
 */
@Component
public class Topic_Consume {
    //加上监听器
    @JmsListener(destination = "${myTopic}")
    public void getMessage(TextMessage textMessage)throws JMSException{
        System.out.println("消费所订阅的主题消息是："+textMessage.getText());
    }
}
