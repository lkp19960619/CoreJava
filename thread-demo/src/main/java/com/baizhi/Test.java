package com.baizhi;

public class Test {
    public static void main(String[] args) {
//        int i = 10;
//        //
//        System.out.println(i++);
//        System.out.println(++i);
        long a = System.currentTimeMillis();
        long result = 0;
        for (int i = 1; i <= 100000; i++) {
            result += i;
        }
        System.out.println(result);
        System.out.println("\r执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
    }
}
