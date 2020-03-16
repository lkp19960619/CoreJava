package com.baizhi;

public class TestThread1 {
    public static void main(String[] args) {
        //创建任务对象
        Task1 task1 = new Task1();
        /**
         * 创建线程对象，线程对象并不是线程，创建线程对象并不是创建多线程，线程对象
         * 是相对于Java而言的，而线程是相对于OS而言的，我们可以通过线程对象来调用方法
         * 到操作系统中开辟一个新线程
         */
        //创建线程对象,并为其分配任务
        Thread t1 = new Thread(task1);
        System.out.println("线程启动");
        //创建线程
        t1.start();
    }
}
class Task1 implements Runnable {
    //run()方法里面的代码就相当于主线程main函数中的代码
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("###" + i);
        }
    }
}
