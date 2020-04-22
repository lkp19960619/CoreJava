package com.baizhi.activemq.Async;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/21 16:07
 * @Version 1.0
 */
public class JmsProduce {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String QUEUE_NAME = "queue-cluster";

    public static void main(String[] args) throws JMSException {

        //1.创建连接工厂,按照给定的url地址
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        //开启生产者异步投递，让生产消息更快
        activeMQConnectionFactory.setUseAsyncSend(true);
        //2.通过连接工厂创建连接对象并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建会话session
        //两个参数：第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地（具体是队列还是主题topic）
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消息的生产者
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);

        //6.通过messageProducer生产消息发送到队列中
        for (int i = 1; i <= 3; i++) {
            //7.创建消息
            TextMessage message = session.createTextMessage("msg---" + i);
            //为生产的消息加上消息头，用于区别每条消息
            message.setJMSMessageID(UUID.randomUUID().toString());
            String msgID = message.getJMSMessageID();
            //8.通过activeMQMessageProducer发送给queue,因为是异步投递，可能存在消息的丢失，需要回调
            activeMQMessageProducer.send(message, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(msgID + "has been send ok");
                }

                @Override
                public void onException(JMSException exception) {
                    System.out.println(msgID + "fail to send to mq");
                }
            });
        }
        //9.关闭资源
        activeMQMessageProducer.close();
        session.close();
        connection.close();
        System.out.println("*****消息发送到MQ完成*****");
    }
}
