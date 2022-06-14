package com.atguigu.pool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *    7个参数
 *          • corePoolSize       线程池的核心线程数
 *          • maximumPoolSize    能容纳的最大线程数
 *          • keepAliveTime      空闲线程存活时间
 *          • unit               存活的时间单位
 *          • workQueue          存放提交但未执行任务的队列
 *          • threadFactory      创建线程的工厂类
 *          • handler            等待队列满后的拒绝策略
 *
 *
 * 线程池底层工作原理(重要)
 * 1. 在创建了线程池后，线程池中的线程数为零
 * 2. 当调用 execute()方法添加一个请求任务时，线程池会做出如下判断：
 *  2.1 如果正在运行的线程数量小于 corePoolSize，那么马上创建线程运行这个任务；
 *  2.2 如果正在运行的线程数量大于或等于 corePoolSize，那么将这个任务放入队列；
 *  2.3 如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；
 *  2.4 如果队列满了且正在运行的线程数量大于或等于 maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。
 * 3. 当一个线程完成任务时，它会从队列中取下一个任务来执行
 * 4. 当一个线程无事可做超过一定的时间（keepAliveTime）时，线程会判断：
 *  4.1 如果当前运行的线程数大于 corePoolSize，那么这个线程就被停掉。
 *  4.2所以线程池的所有任务完成后，它最终会收缩到 corePoolSize 的大小。
 *
 *
 *
 *
 *  拒绝策略
 *  1.CallerRunsPolicy: 当触发拒绝策略，只要线程池没有关闭的话，则使用调用线程直接运行任务。一般并发比较小，
 *  性能要求不高，不允许失败。但是，由于调用者自己运行任务，如果任务提交速度过快，可能导致程序阻塞，性能效率上必然的损失较大
 *  2.AbortPolicy: 丢弃任务，并抛出拒绝执行 RejectedExecutionException 异常信息。线程池默认的拒绝策略。
 *  必须处理好抛出的异常，否则会打断当前的执行流程，影响后续的任务执行。
 *  3.DiscardPolicy: 直接丢弃，其他啥都没有
 *  4.DiscardOldestPolicy: 当触发拒绝策略，只要线程池没有关闭的话，丢弃阻塞队列 workQueue 中最老的一个任务，并将新任务加入
 *
 *
 * @author ChenCheng
 * @create 2022-06-14 21:40
 */
public class ThreadPoolDemo2 {

    public static void main(String[] args) {
        int corePoolSize = 2;      // 核心线程数
        int maximumPoolSize = 5;   // 最大线程数
        long keepAliveTime = 2L;   // 空闲线程存活时间
        TimeUnit unit = TimeUnit.SECONDS;  // 存活的时间单位


        ExecutorService threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                new ArrayBlockingQueue<>(3),  // 阻塞队列
                Executors.defaultThreadFactory(),      // 创建线程的工厂类
                new ThreadPoolExecutor.AbortPolicy()   // 拒绝策略
        );



        //10个顾客请求
        try {
            for (int i = 1; i <=5; i++) {
                //执行
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭
            threadPool.shutdown();
        }


    }

}
