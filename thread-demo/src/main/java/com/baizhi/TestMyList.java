package com.baizhi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestMyList {
    public static void main(String[] args) {

    }
}

/**
 * 读写分离的思路实现线程安全同时又有更高并发效率的ArrayList
 */
class MyList1 extends ArrayList {
    List list = new CopyOnWriteArrayList();
    //创建读写锁对象
    ReadWriteLock rw1 = new ReentrantReadWriteLock();
    //通过读写锁对象分别创建读锁和写锁
    Lock r1 = rw1.readLock();
    Lock w1 = rw1.writeLock();
    //读锁和写锁

    @Override
    public int size() {
        try {
            r1.lock();
            return super.size();
        } finally {
            r1.unlock();
        }
    }

    @Override
    public Object get(int index) {
        try {
            r1.lock();
            return super.get(index);
        } finally {
            r1.unlock();
        }
    }

    @Override
    public boolean add(Object o) {
        try {
            w1.lock();
            return super.add(o);
        } finally {
            w1.unlock();
        }
    }

    @Override
    public Object remove(int index) {
        try {
            w1.lock();
            return super.remove(index);
        } finally {
            w1.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            w1.lock();
            super.clear();
        } finally {
            w1.unlock();
        }
    }
}
