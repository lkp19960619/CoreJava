package com.baizhi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {
    public static void main(String[] args) {
        //新建一个固定的线程池，有两个线程
        ExecutorService es = Executors.newFixedThreadPool(2);
        //新建任务
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    System.out.println("$$$ "+i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    System.out.println("%%% "+i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    System.out.println("*** "+i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //把任务提交给线程池，让它分配线程来执行任务
        es.submit(r1);
        es.submit(r2);
        es.submit(r3);

        //关闭线程池
        es.shutdown();
    }
}
