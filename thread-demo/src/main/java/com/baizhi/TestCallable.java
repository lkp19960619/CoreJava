package com.baizhi;

import java.util.concurrent.*;

/**
 * Callable接口类似于Runnable ，然而，Runnable不返回结果，也不能抛出被检查的异常。
 */
public class TestCallable {
    public static void main(String[] args) throws Exception {
        //根据任务需要创建一个大小可变化的线程池
        ExecutorService es = Executors.newCachedThreadPool();
        Callable<Integer> task1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 1; i <= 100; i += 2) {
                    result += i;
                    Thread.sleep(100);
                }
                return result;
            }
        };
        Callable<Integer> task2 = ()->{
            int result = 0;
            for(int i=2;i<=100;i+=2){
                result +=i;
                Thread.sleep(100);
            }
            return result;
        };

        //把任务提交给线程池
        Future<Integer> f1 = es.submit(task1);  //Future用来存放任务的返回结果
        Future<Integer> f2 = es.submit(task2);

        //从Future中取出结果,并把两个线程里面的结果进行合并
        int result = f1.get() + f2.get();
        System.out.println(result);
    }
}
