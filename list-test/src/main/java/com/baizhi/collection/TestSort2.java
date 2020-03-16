package com.baizhi.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 使用Comparator比较器进行排序对象不需要implements Comparable接口，排序规则定义在比较器中
 */
public class TestSort2 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Huxz", 18),
                new Person("Liucy", 27),
                new Person("Liangb", 35),
                new Person("Suns", 28)
        );
        //比较器一：按年龄排序
        Comparator<Person> c1 = new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.age-p2.age;
            }
        };

        //比较器二：按姓名字典顺序排序
        Comparator<Person> c2 = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        };
        Collections.sort(people,c2);
        for (Person person : people) {
            System.out.println(person);
        }
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}