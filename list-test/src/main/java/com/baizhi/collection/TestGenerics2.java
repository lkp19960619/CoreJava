package com.baizhi.collection;

/**
 * 泛型作为模板编程，泛型类可以规定要使用泛型地方的参数类型
 */
public class TestGenerics2 {
    public static void main(String[] args) {

    }
}

interface I<T> {
    void m(T t);
}

interface N<T> {
    T m();
}

class A implements I<String> {
    @Override
    public void m(String s) {
    }
}

class B implements I<Integer> {
    public void m(Integer i) {
    }
}

class C implements N<String> {
    public String m() {
        return "Hello";
    }
}

class D implements N<Integer>{
    public Integer m() {
        return 1;
    }
}