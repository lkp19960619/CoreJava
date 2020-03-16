package com.baizhi;

/**
 * 对临界资源对象的同步方法加锁
 */
public class Synchronized2 {
    public static void main(String[] args) throws InterruptedException {
        MyList list = new MyList();
        Thread t1 = new Thread(() -> list.add("D"));
        Thread t2 = new Thread(() -> list.add("E"));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        list.add("F");
        list.print();
    }
}
