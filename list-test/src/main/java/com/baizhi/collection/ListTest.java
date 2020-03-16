package com.baizhi.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ListTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("Liucy");
        list.add("Huxz");
        list.add("Suns");
        list.add("Yangdd");
        //调用遍历方法
        //print1(list);
        //print2(list);
        //print3(list);
        print4(list);
    }

    //list集合的迭代器遍历
    static void print1(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) { //判断集合中还有没有元素没被遍历，有一个指针指向第一个元素之前
            Object o = iterator.next();
            String s = (String) o;
            System.out.println(s);
        }
    }

    //JDK1.5中提供的集合遍历方法
    static void print2(Collection collection) {
        for (Object o : collection) {
            String s = (String) o;
            System.out.println(s.toUpperCase());
        }
    }

    //JDK1.8中提供的遍历
    static void print3(Collection collection) {
        collection.forEach(new Consumer() {
            @Override
            public void accept(Object o) {
                String s = (String)o;
                System.out.println(s.toUpperCase());
            }
        });
    }

    static void print4(Collection collection){
        collection.forEach(o -> System.out.println(o.toString().toUpperCase()));
    }
}
