package com.baizhi;

/**
 * 实现Thread类，并且覆盖run方法
 */
public class TestThread2 {
    public static void main(String[] args) {
        //通过多态创建线程对象
        Thread t1 = new Task2();
        //设置线程的优先级，1-10数越大优先级越高
        t1.setPriority(10);
        //启动线程
        t1.start();

        System.out.println("主线程！！");
    }
}

class Task2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("%%%" + i);
        }
    }
}
