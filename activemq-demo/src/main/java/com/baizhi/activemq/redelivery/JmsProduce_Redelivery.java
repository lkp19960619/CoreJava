package com.baizhi.activemq.redelivery;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/21 18:48
 * @Version 1.0
 */
public class JmsProduce_Redelivery {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String QUEUE_NAME = "redelivery_queue";

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 1; i <= 3; i++){
            TextMessage textMessage = session.createTextMessage("redelivery msg"+i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*****MQ生产消息完成*****");
    }
}
