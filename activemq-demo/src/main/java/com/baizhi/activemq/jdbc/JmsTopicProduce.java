package com.baizhi.activemq.jdbc;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/14 1:15
 * @Version 1.0
 */
public class JmsTopicProduce {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String TOPIC_NAME = "jdbc-topic-persistent";

    public static void main(String[] args) throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        //2.根据工厂创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //3.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建主题
        Topic topic = session.createTopic(TOPIC_NAME);
        //5.创建生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //5.1 要用持久化订阅，发送消息者要用DeliveryMode.PERSISTENT模式且在连接之前设定
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //5.2 一定设置完成持久化之后在start这个connection
        connection.start();
        for (int i = 1; i <= 6; i++) {
            //6.创建消息
            TextMessage textMessage = session.createTextMessage("message-persistent-topic:" + i);
            //7.生产者发送消息
            messageProducer.send(textMessage);
        }
        //8.关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*****生产者发送消息完成*****");
    }
}
