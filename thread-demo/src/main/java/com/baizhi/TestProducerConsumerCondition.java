package com.baizhi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProducerConsumerCondition {
    public static void main(String[] args) {
        MyStack1 myStack = new MyStack1();

        //任务一作为生产者线程
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                for (char c = 'A'; c <= 'Z'; c++) {
                    myStack.push(c + "");
                }
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 26; i++) {
                    myStack.pop();
                }
            }
        };
        //生产者线程
        new Thread(task1).start();
        //消费者线程
        new Thread(task2).start();
    }
}
class MyStack1 {
    String[] data = new String[]{"", "", "", "", "", ""};
    int index;
    //创建锁对象
    Lock lock = new ReentrantLock();
    //创建线程等待队列，每一个Condition对象就相当于一个等待队列
    Condition full = lock.newCondition();
    //创建消费者等待队列
    Condition empty = lock.newCondition();


    //进栈方法,同步方法解决在多线程情况下原子操作对同一对象数据不一致
    public  void push(String s) {
        try {
            //上锁
            lock.lock();
            //如果数组满了，那么生产者线程释放锁标记，等待
            while (data.length == index) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(s + "  pushed  ");
            //把元素放在栈对应的角标上
            data[index] = s;
            //角标自增,指向栈顶元素上一个位置
            index++;
            print();
            //当产生新的元素后通知消费者
            empty.signalAll();
        } finally {
            //解锁
            lock.unlock();
        }
    }

    //出栈方法
    public synchronized String pop() {
        String str;
        try {
            lock.lock();
            //判断数组是否为空
            while (index == 0) {
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //首先让index指向栈顶元素
            index--;
            str = data[index];
            //把原角标位置置为空串
            data[index] = "";
            System.out.print(str + "  poped   ");
            print();
            //如果有元素出栈，那么说明栈不满，通知等待的线程-->通知生产者队列
            full.signalAll();
        } finally {
            lock.unlock();
        }
        return str;
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
