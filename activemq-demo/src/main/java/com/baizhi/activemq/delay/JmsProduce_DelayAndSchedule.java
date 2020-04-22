package com.baizhi.activemq.delay;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/21 17:59
 * @Version 1.0
 */
public class JmsProduce_DelayAndSchedule {
    public static final String ACTIVEMQ_URL = "tcp://192.168.170.20:61616";
    public static final String QUEUE_NAME = "delay-queue";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
//        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //延迟时间
        long delay = 3 * 1000;
        //间隔时间
        long period = 4 * 1000;
        //重复次数
        int repeat = 4;
        for (int i = 1; i <= 3; i++) {
            TextMessage message = session.createTextMessage("delay msg---" + i);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);
            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeat);
            messageProducer.send(message);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*****消息发送到MQ完成*****");
    }
}
