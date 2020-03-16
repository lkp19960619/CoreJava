package com.baizhi.set;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Set集合中的元素内容无序，不可重复，无下标
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Liucy");
        set.add("Huxz");
        set.add("Suns");
        set.add("Liangb");
        set.add("Liucy");
//        System.out.println(set.size());
        print3(set);
    }
    //迭代器遍历
    static <T> void print1(Collection<T> col){
        //创建迭代器对象
        Iterator<T> iterator = col.iterator();
        //通过迭代器对象进行遍历
        while(iterator.hasNext()){
            T t = iterator.next();
            System.out.println(t);
        }
    }
    //for-each遍历
    static <T> void print2(Collection<T> col){
        for (T t : col) {
            System.out.println(t);
        }
    }

    //forEach遍历，JDK8提供
    static <T> void print3(Collection<T> col){
//        col.forEach(new Consumer<T>() {
//            @Override
//            public void accept(T t) {
//                System.out.println(t);
//            }
//        });
        col.forEach(t->{
            System.out.println(t);
        });
    }
}
