package com.baizhi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(6);
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        queue.put("A"+i);
                        for (String s : queue) {
                            System.out.print(s + " 生产者生产");
                        }
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100;i++){
                    try {
                        queue.take();
                        for (String s : queue) {
                            System.out.print(s+" 消费者消费");
                        }
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(task1);
        es.submit(task2);
        es.shutdown();
    }
}
