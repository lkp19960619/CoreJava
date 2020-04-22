package com.baizhi.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/2 23:09
 * @Version 1.0
 */
public class JmsProduce {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String TOPIC_NAME = "jdbc-topic-persistent";

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        //2.通过连接工厂创建连接对象并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.通过连接对象创建session会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //5.创建生产者
        MessageProducer producer = session.createProducer(topic);
        //生产者持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //6.生产消息
        for (int i = 1; i <= 3; i++){
            //7.创建消息
            TextMessage message = session.createTextMessage("topic--msg"+i);
            //8.通过producer发送给主题
            producer.send(message);
        }
        //关闭资源
        producer.close();
        session.close();
        connection.close();
        System.out.println("***消费者生产消息完成");
    }
}
