package com.baizhi.collection;

import java.util.Arrays;

/**
 * List数组的实现
 */
public class MyListTest {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.add("123");
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
//        list.print();
        System.out.println(list.size());
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}

class MyList {
    //定义一个对象类型的数组
    private Object[] os = new Object[3];
    private int index;//记录数组中真正存储了几个元素

    public void add(Object o) {
        //扩容判断
        if (index == os.length) {
            os = Arrays.copyOf(os, os.length * 2);
        }
        //把对象添加到数组中
        os[index] = o;
        index++;
    }

    //返回数组的下标
    public int size(){
        return index;
    }

    //返回数组指定位置的元素
    public Object get(int i){
        return os[i];
    }
}