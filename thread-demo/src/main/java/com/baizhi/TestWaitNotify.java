package com.baizhi;

public class TestWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
                System.out.println("B");
                synchronized (o){ //对o加锁
                    //该方法不会释放锁，只是一个通知的作用
                    o.notify();
                }
                System.out.println("C");
                System.out.println("D");
            }
        });
        t1.start();
        synchronized (o) {
            System.out.println("1");
            System.out.println("2");
            //主线程释放锁标记，主线程进入等待状态
            o.wait();
            System.out.println("3");
            System.out.println("4");
        }
    }
}
