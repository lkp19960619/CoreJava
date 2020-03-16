package com.baizhi.set;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestHashSet {
    public static void main(String[] args) {
        Set<Student> student = new HashSet<>();
        Student s1 = new Student("Huxz", 18);
        Student s2 = new Student("Liucy", 35);
        Student s3 = new Student("Liucy", 35);
        Student s4 = new Student("Suns", 27);
        Student s5 = new Student("Liangb", 26);
        student.add(s1);
        student.add(s2);
        student.add(s3);
        student.add(s4);
        student.add(s5);
        print(student);
    }

    static <T> void print(Collection<T> col) {
        for (T t : col) {
            System.out.println(t);
        }
    }
}

class Student {
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
    public int hashCode() {
        return age+name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //自反性的判断
        if (this == obj) return true;
        //非空判断
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        //强制类型转换
        Student s = (Student) obj;
        if (this.name.equals(s.name) && this.age == s.age) return true;
        else return false;
    }
}
