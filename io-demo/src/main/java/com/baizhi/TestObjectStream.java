package com.baizhi;
import	java.io.ObjectInputStream;
import	java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

/**
 * 对象输入/输出节点流的过滤流，可以对8种基本数据类型，字符串和对象进行操作，并且自带缓冲区
 */
public class TestObjectStream {
    public static void main(String[] args) throws Exception{
        Student s1 = new Student(1, "Huxz", 50.0);
        Student s2 = new Student(2, "Liucy", 80.6);
        //把学生对象写到文件中
        FileOutputStream fos = new FileOutputStream("student.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);
        oos.writeObject(s2);
        oos.flush();
        oos.close();
        FileInputStream fis = new FileInputStream("student.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o1 = ois.readObject();
        Object o2 = ois.readObject();
        ois.close();
        System.out.println(o1);
        System.out.println(o2);
    }
}
class Student implements Serializable {
    int id;
    String name;
    transient double score;

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
