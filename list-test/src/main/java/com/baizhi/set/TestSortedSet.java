package com.baizhi.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet是Set接口子接口SortedSet接口的实现类，它可以自动对元素进行排序，
 * 当然存储的元素类型要实现了Comparable接口，或者有一个自定义的Comparator，
 * 因为TreeSet默认有一个构造方法，方法的形参是一个比较器对象
 *
 * Tree去除相同对象的方式不是靠哈希算法，而是靠比较器的比较逻辑，如果比较器的比较逻辑
 * 结果为0，那么后来的对象就添加不进去，所以如何实现比较逻辑就显得至关重要
 */
public class TestSortedSet {
    public static void main(String[] args) {
        Comparator<Worker> c = new Comparator<Worker>() {
            @Override
            public int compare(Worker o1, Worker o2) {
                if(o1.age!=o2.age)return o1.age-o2.age;
                else return o1.name.compareTo(o2.name);
            }
        };
        Set<Worker> workers = new TreeSet<>(c);
        workers.add(new Worker("Huxz", 18));
        workers.add(new Worker("Liucy", 30));
        workers.add(new Worker("Liucy", 30));
        workers.add(new Worker("Suns", 35));
        workers.add(new Worker("Liangb", 28));
        for (Worker w : workers){
            System.out.println(w);
        }
    }

}

class Worker {
    String name;
    int age;

    public Worker(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
