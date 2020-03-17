package com.baizhi;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 利用ForkJoin找出一个非常庞大的数组中的最大值
 */
public class TestForkJoinMax {
    public static void main(String[] args) {
        Random random = new Random();
        int[] data = new int[40000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100000000);
        }
        System.out.println(Task(data));

    }

    static Integer Task(int[] data) {
        class FindMax extends RecursiveTask<Integer> {
            int data[];
            int start;
            int end;
            static final int THRESHOLD = 10000;

            public FindMax(int[] data, int start, int end) {
                this.data = data;
                this.start = start;
                this.end = end;
            }

            @Override
            protected Integer compute() {
                //达到阈值直接执行
                if(end - start <= THRESHOLD){
                    int max = data[start];
                    for(int i = start+1;i<end;i++){
                        if(data[i]>max)max = data[i];
                    }
                    return max;
                }else{
                    int middle = (start+end)/2;
                    FindMax task1 = new FindMax(data, start, middle);
                    FindMax task2 = new FindMax(data, middle, end);
                    invokeAll(task1,task2);
                    //获得任务一的结果
                    Integer result1 = task1.join();
                    Integer result2 = task2.join();
                    if(result1>result2)return result1;
                    else return result2;
                }
            }
        }
        ForkJoinPool pool = new ForkJoinPool();
        FindMax main = new FindMax(data, 0, data.length);
        Integer result = pool.invoke(main);
        return result;
    }
}
