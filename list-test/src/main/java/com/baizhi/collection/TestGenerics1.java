package com.baizhi.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型的通配符
 */
public class TestGenerics1 {
    public static void main(String[] args) {
        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();     //implements    Comparable
        List<Number> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();    //extends Number implements Comparable
        List<Double> list5 = new ArrayList<>();     //extends Number implements Comparable
        List<Comparable> list6 = new ArrayList<>();

//        print(list1);
//        print(list2);
//        print(list3);
//        print(list4);
//        print(list5);
//        print(list6);

    }

//    static void print(List<?> list) {    //?作为泛型的通配符，用在对方法的形参定义类型上
//    }
//    static void print(List<? extends Number> list) {//? extends A:表示A或A的任何子类，用在对方法的形参定义类型上
//    }

//    static void print(List<? extends Comparable> list){
//    }
    static void print(List<? super Number> list){
        //? super A:表示A或者A的父类，用在对方法的形参定义类型上
        for (Object o : list) {
            Object object = list.get((Integer) o);
        }
    }

    static <T> void copy(T[] t,List<T> list){   //泛型方法用来解决前后传递参数类型不一致的情况
        for (T t1 : t) {
            list.add(t1);
        }
    }

    static <T> void copy1(List<? super T> dest,List<? extends T> src){

    }
}
