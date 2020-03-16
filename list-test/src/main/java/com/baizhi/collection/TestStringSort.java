package com.baizhi.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * JDK8中提供List比较方法
 */
public class TestStringSort {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("一","三","五","七","八");
        Comparator<String> c1 = Comparator.naturalOrder();
        list.sort(c1);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
