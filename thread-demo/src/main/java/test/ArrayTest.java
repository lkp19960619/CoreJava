package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArrayTest {
    public static void main(String[] args) {
        int[] arr = createArray(20);
        //为数组赋值
        assignmentArray(arr);
        print(arr);
    }

    //创建数组
    static int[] createArray(int N) {
        return new int[N];
    }

    //赋值数组
    static void assignmentArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int result = (int) (Math.random() * 50 + 1);
            if (result % 2 == 0)
                array[i] = result;
        }
    }

    //打印数组
    static void print(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
            Integer num = map.get(array[i]);
            map.put(array[i], num == null ? 1 : num + 1);
        }
        System.out.println();
        int[] arr = {};
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            int index = 0;
            arr = new int[]{entry.getValue()};
            System.out.println(entry.getKey() + "  " + entry.getValue() + "  " + map.size() + "  " + arr.length);
        }
        System.out.println("------------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

}
