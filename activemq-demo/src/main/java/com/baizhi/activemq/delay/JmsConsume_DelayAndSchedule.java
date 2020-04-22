package com.baizhi.activemq.delay;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/21 18:04
 * @Version 1.0
 */
public class JmsConsume_DelayAndSchedule {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String QUEUE_NAME = "delay-queue";

    public static void main(String[] args) throws JMSException, IOException {
        //1.创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        //2.通过连接工厂创建连接并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建session会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.通过会话创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //6.通过监听的方式来消费消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //对message做判断
                if(null != message && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("*****消费者接收到消息*****"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //7.保持控制台不中断
        System.in.read();
        //8.关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
