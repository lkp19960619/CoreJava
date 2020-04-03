package com.baizhi.activemq.produce;

import com.baizhi.activemq.SpringStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @Author: 李开鹏
 * @Date: 2020/4/3 13:42
 * @Version 1.0
 */
@SpringBootTest(classes = SpringStarter.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class Queue_ProduceTest {
    @Autowired
    private Queue_Produce queue;

    @Test
    public void testSend() throws Exception {
        queue.productMsg();
    }
}