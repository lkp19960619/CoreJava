package com.baizhi.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 16:26
 * @Version 1.0
 */
@Component
public class Topic_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    public void productMsg(){
        jmsMessagingTemplate.convertAndSend(topic,"***产生消息***："+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("send task over");
    }

    //定时任务
    @Scheduled(fixedDelay = 3000l)
    public void productScheduled(){
        jmsMessagingTemplate.convertAndSend(topic,"***产生消息***："+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("productScheduled task over");
    }
}
