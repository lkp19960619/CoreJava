package com.baizhi.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 13:36
 * @Version 1.0
 */
@Component
public class Queue_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    public void productMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"***:"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("send task over");
    }
    //间隔定投
    @Scheduled(fixedDelay = 3000l)
    public void productScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"***:"+ UUID.randomUUID().toString());
        System.out.println("***productScheduled send over");
    }
}
