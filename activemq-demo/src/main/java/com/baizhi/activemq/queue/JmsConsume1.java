package com.baizhi.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/2 22:13
 * @Version 1.0
 */
public class JmsConsume1 {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin","admin",ACTIVEMQ_URL);
        //2.通过连接工厂创建连接对象并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.通过连接对象创建session会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消费者对象
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true) {
            TextMessage receive = (TextMessage) messageConsumer.receive(4000l);
            if (null != receive) {
                System.out.println("*****消费者接收到消息*****" + receive.getText());
            } else {
                break;
            }
        }
        //6.关闭资源
        messageConsumer.close();
        session.close();
        connection.close();

    }
}
