package com.atguigu.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *
 * Fork/Join 框架
 * Fork/Join 它可以将一个大的任务拆分成多个子任务进行并行处理，最后将子
 * 任务结果合并成最后的计算结果，并进行输出。Fork/Join 框架要完成两件事情：
 * Fork：把一个复杂任务进行分拆，大事化小
 * Join：把分拆任务的结果进行合并
 * 1. 任务分割：首先 Fork/Join 框架需要把大的任务分割成足够小的子任务，如果
 * 子任务比较大的话还要对子任务进行继续分割
 * 2. 执行任务并合并结果：分割的子任务分别放到双端队列里，然后几个启动线程
 * 分别从双端队列里获取任务执行。子任务执行完的结果都放在另外一个队列里，
 * 启动一个线程从队列里取数据，然后合并这些数据。
 * 在 Java 的 Fork/Join 框架中，使用两个类完成上述操作
 *
 * • ForkJoinTask:我们要使用 Fork/Join 框架，首先需要创建一个 ForkJoin 任务。
 * 该类提供了在任务中执行 fork 和 join 的机制。通常情况下我们不需要直接集成 ForkJoinTask 类，
 * 只需要继承它的子类，Fork/Join 框架提供了两个子类：
 * a.RecursiveAction：用于没有返回结果的任务
 * b.RecursiveTask:用于有返回结果的任务
 *      • ForkJoinPool:ForkJoinTask 需要通过 ForkJoinPool 来执行
 *      • RecursiveTask: 继承后可以实现递归(自己调自己)调用的任务
 *
 * 场景: 生成一个计算任务，计算 1+2+3.........+1000,每100 个数切分一个子任务
 *
 * @author ChenCheng
 * @create 2022-06-14 22:47
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0,100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        //获取最终合并之后结果
        Integer result = forkJoinTask.get();
        System.out.println(result);
        //关闭池对象
        forkJoinPool.shutdown();
    }
}



class MyTask extends RecursiveTask<Integer>{

    //拆分差值不能超过10，计算10以内运算
    private static final Integer VALUE = 10;
    private int begin ;//拆分开始值
    private int end;//拆分结束值
    private int result ; //返回结果

    //创建有参数构造
    public MyTask(int begin,int end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并过程
    @Override
    protected Integer compute() {
        //判断相加两个数值是否大于10
        if((end-begin) <= VALUE){
            // 执行相加的操作
            for (int i = begin; i <=end; i++) {
                result = result+i;
            }
        }else {
            // 进一步拆分
            //获取中间值
            int middle = (begin+end)/2;
            //拆分左边
            MyTask task01 = new MyTask(begin,middle);
            //拆分右边
            MyTask task02 = new MyTask(middle+1,end);

            //调用方法拆分
            task01.fork();
            task02.fork();

            //合并结果
            result = task01.join()+task02.join();

        }


        return result;
    }



}


