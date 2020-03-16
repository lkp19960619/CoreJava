package com.baizhi;

/**
 * 数字和字母交替打印
 */
public class TestNumberCharPrint {
    public static void main(String[] args) {
        Object o = new Object();
        //数字线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    for (int i = 1; i <= 52; i++) {
                        System.out.println(i);
                        if (i % 2 == 0) {
                            //通知子母线程，释放子母线程
                            o.notifyAll();
                            try {
                                if (i != 52)
                                    o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();

        //字母线程
        new Thread(() -> {
            synchronized (o) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    System.out.println(c);
                    //释放数字线程
                    o.notifyAll();
                    try {
                        if (c != 'Z')
                            o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
