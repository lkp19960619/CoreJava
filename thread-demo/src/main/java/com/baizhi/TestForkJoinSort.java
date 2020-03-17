package com.baizhi;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 使用RecursiveAction对把一个大数组拆分成小数组，分段排序
 */
public class TestForkJoinSort {
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        Random random = new Random();
        int[] data = new int[5000000];
        for (int i=0;i < data.length; i++){
            data[i] = random.nextInt(5000000);
        }
        //创建ForkJoinPool对象
//        ForkJoinPool pool = new ForkJoinPool();
//        MySortTask task = new MySortTask(data, 0, data.length);
//        //提交任务
//        pool.invoke(task);
        Arrays.parallelSort(data);
        for(int i = 0;i<data.length;i++){
            System.out.println(data[i]);
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-a)+"毫秒");
    }
}

class MySortTask extends RecursiveAction {
    int[] data;
    //起始下标  包含
    int start;
    //结束下标  不包含
    int end;
    //阈值
    static final int THRESHOLD = 1000;

    public MySortTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        //如果达到阈值，直接计算
        if (end - start <= THRESHOLD) {
            Arrays.sort(data, start, end);
        } else {  //否则，把一个数组拆分成子数组
            int middle = (start + end) / 2;
            //子任务一：数组下标从开始到middle，不包括middle
            MySortTask task1 = new MySortTask(data, start, middle);
            //子任务二：数组下标从middle(包括middle)到end(不包括end)
            MySortTask task2 = new MySortTask(data, middle, end);
            //执行子任务
            invokeAll(task1, task2);
            //两个子任务完成之后，需要进行数组的合并
            merge(middle);
        }
    }

    //将两个有序的数组合并成有序的大数组
    void merge(int middle) {
        //从原数组中拷贝数组一排序后的数组
        int[] a = Arrays.copyOfRange(data, start, middle);
        //从原数组中拷贝数组二排序后的数组
        int[] b = Arrays.copyOfRange(data, middle, end);

        //定义变量表示用数组a的哪个角标参与比较
        int x = 0;
        //定义变量表示用数组b的哪个角标参与比较
        int y = 0;
        //把两个数组进行合并，需要两个数组中的元素进行比较
        for (int i = start; i < end; i++) {
            //如果数组a参与比较的位置达到最大，把b数组开始赋值给大数组
            if (a.length == x) data[i] = b[y++];
            else if (b.length == y) data[i] = a[x++];
            else if (a[x] < b[y]) data[i] = a[x++];
            else data[i] = b[y++];
        }
    }
}

