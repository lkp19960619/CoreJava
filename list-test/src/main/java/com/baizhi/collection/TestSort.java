package com.baizhi.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestSort{
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Huxz", 18),
                new Student("Liucy", 27),
                new Student("Liangb", 35),
                new Student("Suns", 28)
        );
        Collections.sort(students);

        for (Student student : students) {
            System.out.println(student);
        }
    }
}

class Student implements Comparable<Student> { //要想对象可排序必须implements Comparable接口
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
//        if(this.age>o.age){
//            return 1;   //正整数表示大于
//        }else if(this.age<o.age){
//            return -1;  //负整数表示小于
//        }else{
//            return 0;   //0表示等于
//        }
        return this.age-o.age;
    }
}