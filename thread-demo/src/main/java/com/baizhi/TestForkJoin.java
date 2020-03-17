package com.baizhi;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 当分配给一个线程的任务执行完成之后，分配给该线程的CPU处于空闲状态
 * 它会从别的线程中拿到还未完成的任务，帮助完成任务，这个算法叫做工作窃取算法
 * 适用于多核CPU
 * <p>
 * 把一个大任务拆分成多个小任务，实现并行计算，当所有的小任务计算完成之后
 * 把计算结果进行合并
 */
public class TestForkJoin {
    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        System.out.println(Runtime.getRuntime().availableProcessors());
        //创建一个ForkJoin对象
        ForkJoinPool pool = new ForkJoinPool();

        AddTask main = new AddTask(1,100000);

        //提交任务
        Long result = pool.invoke(main);
        System.out.println("结果为："+result);
        System.out.println("\r执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
    }
}

//编写任务类，这个任务类既表示大任务，又表示小任务
class AddTask extends RecursiveTask<Long> {
    //定义两个变量表示任务的开始和结束
    int start;
    int end;
    //定义一个阈值表示在什么条件下执行任务
    static final int THRESHOLD = 5000;

    public AddTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    //任务的实际计算
    @Override
    protected Long compute() {
        //如果start和end之间的差值小于THRESHOLD，直接进行计算
        if (end - start <= THRESHOLD) {
            long result = 0;
            for (int i = start; i <= end; i++) {
                result += i;
            }
            return result;
        }
        //否则就要把任务划分成两个子任务
        else {
            int middle = (start+end)/2;
            AddTask task1 = new AddTask(start,middle);
            AddTask task2 = new AddTask(middle+1,end);
            //让子任务执行
            invokeAll(task1,task2);
            //执行完成之后进行任务合并
            Long r1 = task1.join();
            Long r2 = task2.join();
            return r1+r2;
        }
    }
}
