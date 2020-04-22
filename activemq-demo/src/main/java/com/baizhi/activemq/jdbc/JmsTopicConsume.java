package com.baizhi.activemq.jdbc;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/14 1:28
 * @Version 1.0
 */
public class JmsTopicConsume {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String TOPIC_NAME = "jdbc-topic-persistent";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        //根据工厂创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("baizhi01");
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建订阅者
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark");
        //启动连接对象
        connection.start();
        //接收消息
        Message message = subscriber.receive();
        while (null != message){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("***收到的持久化消息是***" + textMessage.getText());
            //延迟关闭
            message = subscriber.receive(3000l);
        }
        session.close();
        connection.close();
    }
}
