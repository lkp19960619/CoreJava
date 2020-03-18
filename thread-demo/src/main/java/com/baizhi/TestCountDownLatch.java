package com.baizhi;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(2);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("### " + i);
                    //判断达到某种条件释放等待的线程
                    if (i == 50) cdl.countDown();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("$$$ " + i);
                    if (i == 50) cdl.countDown();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                //让线程等待
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 1; i <= 100; i++) {
                    System.out.println("%%% " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
    }
}
