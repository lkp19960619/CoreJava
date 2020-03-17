package com.baizhi;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 十个线程，每个线程计算i的累加结果
 * <p>
 * i++和++i
 * 对于i++
 * i存在一个变量里，在我们做运算的时候，首先把i取出来放在一个寄存器里面，
 * 在寄存器里面实现+1的操作之后再把结果写回到变量里
 * <p>
 * ++i
 * 先进行自增再参与运算
 * i存在一个变量里面，把i拷贝到一个寄存器，进行+1操作，把结果发送给原变量，
 * 覆盖原变量的值再参与运算
 * <p>
 * java.util.concurrent.atomic是一个小型的工具包，
 * 支持单个变量上的无锁线程安全编程，有很多的实现类
 * 所谓所谓算法，就是CAS算法-->比较和替换
 */
public class TestAtomicInteger {
    static int i = 0;
    static AtomicInteger a = new AtomicInteger(0);
    static MyObject obj = new MyObject();

    public static void main(String[] args) throws Exception {
        //创建线程数组
        Thread[] ts = new Thread[10];
        for (int k = 0; k < ts.length; k++) {
            //为每个线程分配具体的任务
            ts[k] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10000; j++) {
                        i++;
                        a.getAndIncrement();
                        synchronized (obj){
                            obj.i++;
                        }
                    }
                }
            });
            //把线程交给OS
            ts[k].start();
        }
        for (int k = 0; k < ts.length; k++) {
            ts[k].join();
        }
        System.out.println(i);
        System.out.println(a);
        System.out.println(obj.i);
    }
}

class MyObject {
    public int i = 0;
}
