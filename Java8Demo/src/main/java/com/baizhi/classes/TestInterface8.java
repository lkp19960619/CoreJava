package com.baizhi.classes;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TestInterface8 {
    public static void main(String[] args) {
        Animal dog = Animal.createDog();
        Animal cat = Animal.createCat();
        dog.eat();
        cat.eat();
        //获取比较器对象，通过定义好的Comparator接口调用对应的静态方法
        Comparator<String> c1 = Comparator.naturalOrder();
        List<String> list = Arrays.asList("liucy","Huxz","liangb","suns");
        list.sort(c1);
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
//在java8中，在接口中可以声明静态方法
interface Animal{
    void eat();
    //在接口中暴露两个静态方法，然后使用匿名内部类的方法可以创建对象
    static Animal createDog(){
        class Dog implements Animal{
            public void eat(){
                System.out.println("Dog eat");
            }
        }
        return new Dog();
    }

    static Animal createCat(){
        class Cat implements Animal{
            public void eat() {
                System.out.println("Cat eat");
            }
        }
        return new Cat();
    }
}
interface IA{
    void m1();
    default void m2(){}
    default void m3(){}
}
class IAImpl implements IA{

    @Override
    public void m1() {

    }
}

