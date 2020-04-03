package com.baizhi.activemq.topic.persisitent;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 10:18
 * @Version 1.0
 */
public class JmsConsume {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        System.out.println("消费者1");
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        //通过连接工厂创建连接对象
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("消费者1");
        //通过连接对象创建session会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建订阅者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark");
        //启动连接对象
        connection.start();
        //接收消息
        Message message = topicSubscriber.receive();
        while (null != message) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("***收到的持久化消息是***" + textMessage.getText());
            //延迟关闭
            message = topicSubscriber.receive(3000l);
        }

        session.close();
        connection.close();

    }
}
