package com.baizhi;

/**
 * 如果一个线程失去CPU会导致整个程序的运行出现问题
 * 当多线程共同访问同一个对象(临界资源)的时候，如果破坏了不可分割操作(原子操作)，就可能发生数据不一致
 */
public class Synchronized1 {
    public static void main(String[] args) throws Exception {
        //局部内部类或者匿名内部类访问外部类的局部变量需要加final
        final MyList list = new MyList();
//        list.add("C");
//        list.add("D");
        Thread t1 = new Thread(() -> {
            //对临界资源对象加互斥锁，解决多线程下访问同一个对象造成数据不一致的问题
            synchronized (list) {
                //原子操作
                list.add("C");
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (list) {
                list.add("D");
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        list.add("E");
        list.print();
    }
}

class MyList {
    String[] data = {"A", "B", "", "", ""};
    //定义一个变量记录data数组中有效元素的个数
    int index = 2;

    public synchronized void add(String s) {
        data[index] = s;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        index++;
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }
}
