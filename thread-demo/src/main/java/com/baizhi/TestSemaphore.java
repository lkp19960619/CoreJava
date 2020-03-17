package com.baizhi;

import java.util.concurrent.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 使用Semaphore解决电话亭问题
 * 电话亭问题：如果有五个电话亭(锁)，有一堆人(线程)需要使用电话亭，而前面五个人
 * 都分别占了一个电话亭，那么第六个人该进入哪个电话亭才是最快的呢？
 * 这就用到了Semaphore，它采用轮询的方法，找到有空闲的电话亭分别让等待的人进入
 */
public class TestSemaphore {
    public static void main(String[] args) {
        //创建五个电话亭
        List<PhoneRoom> rooms = new ArrayList<>();
        rooms.add(new PhoneRoom("room 1"));
        rooms.add(new PhoneRoom("room 2"));
        rooms.add(new PhoneRoom("room 3"));
        rooms.add(new PhoneRoom("room 4"));
        rooms.add(new PhoneRoom("room 5"));

        //创建Semaphore对象并为其设置许可证数量，许可证就相当于锁
        Semaphore semaphore = new Semaphore(5);


        class Task implements Runnable {

            @Override
            public void run() {
                //尝试拿到一张通行证，拿不到说明房间都满了，需要等待
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < rooms.size(); i++) {
                    PhoneRoom room = rooms.get(i);
                    //如果有一个电话亭为空
                    if (room.isFree.get()) {
                        //进入电话亭后相当于上锁，电话亭的状态设为false
                        room.isFree.set(false);
                        System.out.println(Thread.currentThread().getName() + " entered " + room.getName());
                        try {
                            //打电话
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //离开电话亭
                        System.out.println(Thread.currentThread().getName() + " exited " + room.getName());
                        room.isFree.set(true);
                        //出来之后释放许可证
                        semaphore.release();
                        return;
                    }
                }
            }
        }

        //创建Task对象
        for (int i = 1; i <= 10; i++) {
            Runnable task = new Task();
            Thread thread = new Thread(task);
            thread.start();
        }

    }
}

//电话亭实体类
class PhoneRoom {
    //isFree表示电话亭的状态
    AtomicBoolean isFree = new AtomicBoolean(true);
    //电话亭的名字
    String name;

    public PhoneRoom(String name) {
        this.name = name;
    }

    public AtomicBoolean getIsFree() {
        return isFree;
    }

    public void setIsFree(AtomicBoolean isFree) {
        this.isFree = isFree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
