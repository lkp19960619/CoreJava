package com.baizhi.classes;

import java.util.ArrayList;
import java.util.List;

public class TestLambda1 {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("zhangsan", 23, 85.0, "3"));
        list.add(new Student("lisi", 19, 56.0, "2"));
        list.add(new Student("wangwu", 40, 99.0, "1"));
        list.add(new Student("zhaoliu", 35, 100.0, "4"));
        list.add(new Student("tianqi", 36, 34.0, "6"));
        list.add(new Student("wangba", 15, 65.0, "5"));
//        List<Student> students = filterStudent(list);
        //在调用方法的时候要写接口的实现
        List<Student> students = filterStudent(list, new Filter() {
            @Override
            public boolean test(Student s) {
                if (s.age > 30) return true;
                else return false;
            }
        });
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("=============================华丽的分割线===============================");
        List<Student> students1 = filterStudent(list, new Filter() {
            @Override
            public boolean test(Student s) {
                if (s.score > 60) return true;
                else return false;
            }
        });
        for (Student student : students1) {
            System.out.println(student);
        }
        System.out.println("=============================华丽的分割线===============================");
        List<Student> students2 = filterStudent(list, (s) -> s.getAge() > 30);
        for (Student student : students2) {
            System.out.println(student);
        }
    }

    //需求：把满足条件的学生挑到另一个集合中
//    static List<Student> filterStudent(List<Student> list){
//        //创建一个新的集合
//        List<Student> newList = new ArrayList<>();
//        for (Student student : list) {
//            if(student.age>30)newList.add(student);
//        }
//        return newList;
//    }
    static List<Student> filterStudent(List<Student> list, Filter filter) {
        //创建一个新的集合
        List<Student> newList = new ArrayList<>();
        for (Student student : list) {
            //如果满足接口中的test方法，则把这个对象添加到一个新的集合中
            if (filter.test(student)) newList.add(student);
        }
        return newList;
    }
}

interface Filter {
    boolean test(Student s);
}

class Student {
    String name;
    int age;
    double score;
    String classNumber;

    public Student(String name, int age, double score, String classNumber) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.classNumber = classNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", classNumber='" + classNumber + '\'' +
                '}';
    }
}