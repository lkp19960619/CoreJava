package com.baizhi.activemq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 11:25
 * @Version 1.0
 */
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        //ActiveMQ也支持在JVM通信中基于嵌入式的Broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
