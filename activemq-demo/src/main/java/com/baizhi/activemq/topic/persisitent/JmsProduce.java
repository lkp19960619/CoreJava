package com.baizhi.activemq.topic.persisitent;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 10:11
 * @Version 1.0
 */
//持久化的topic
public class JmsProduce {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        //设置主题持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //启动连接对象
        connection.start();
        for(int i = 1; i <= 3; i++){
            //生产消息
            TextMessage textMessage = session.createTextMessage("msg--persistent" + i);
            //通过生产者把消息发送给主题
            producer.send(textMessage);
        }
        //关闭资源
        producer.close();
        session.close();
        connection.close();
        System.out.println("***带持久化的Topic消息发送到MQ完成***");
    }

}
