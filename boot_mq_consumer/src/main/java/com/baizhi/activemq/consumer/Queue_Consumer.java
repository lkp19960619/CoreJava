package com.baizhi.activemq.consumer;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 15:55
 * @Version 1.0
 */
@Component
public class Queue_Consumer {
    @JmsListener(destination = "${myqueue}")
    public void getMessage(TextMessage textMessage) throws JMSException {
        System.out.println("消费者消费："+textMessage.getText());
    }
}
