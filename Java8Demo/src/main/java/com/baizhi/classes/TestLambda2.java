package com.baizhi.classes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestLambda2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("ABC","DEF","HIJ");
        //普通的遍历方式
        list.forEach(new Consumer<String>(){
            public void accept(String s){
                System.out.println(s);
            }
        });
        System.out.println("====================华丽的分割线====================");
        //Lambda方式遍历
        list.forEach(s-> System.out.println(s));
        System.out.println("====================华丽的分割线====================");
        //方法引用的方式遍历
        list.forEach(System.out::println);
    }
}
