package com.baizhi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock解决死锁问题，不需要再使用synchronized方法或者代码块，
 * 因为synchronized会让线程死等被锁标记的对象，而Lock则可以更加灵活的实现
 */
public class TestLock {
    public static void main(String[] args) throws InterruptedException {
        //创建共享资源对象
        AList list = new AList();
        Thread t1 = new Thread(() -> list.add("C"));
        Thread t2 = new Thread(() -> list.add("D"));
        //把线程交给OS管理
        t1.start();
        t2.start();
        //让线程等待
        t1.join();
        t2.join();
        list.print();
    }
}

class AList {
    String[] data = {"A", "B", "", "", ""};
    //定义一个变量记录data数组中有效元素的个数
    int index = 2;
    //创建一个重入锁
    Lock lock = new ReentrantLock();

    //对原子操作上锁
    public void add(String s) {
        try {
            //上锁
            lock.lock();
            data[index] = s;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
        }
        //解锁
        finally {
            lock.unlock();
        }
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }
}
