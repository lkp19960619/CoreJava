package com.baizhi.activemq.redelivery;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/21 18:54
 * @Version 1.0
 */
public class JmsConsume_Redelivery {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String QUEUE_NAME = "redelivery_queue";

    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        //改变重收消息策略
        RedeliveryPolicy queuePolicy = new RedeliveryPolicy();
        queuePolicy.setMaximumRedeliveries(3);
        activeMQConnectionFactory.setRedeliveryPolicy(queuePolicy);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        TextMessage message = null;
        while(true){
            message = (TextMessage) messageConsumer.receive(1000L);
            if(null != message){
                System.out.println("收到消息的内容是："+message.getText());
            }else{
                break;
            }
        }
//        session.commit();
        session.close();
        connection.close();
        System.out.println("*****消费者完成收到消息*****");
    }
}
