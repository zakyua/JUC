package com.atguigu.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 演示线程池三种常用分类
 *
 * 线程池（英语：thread pool）：一种线程使用模式。线程过多会带来调度开销，
 * 进而影响缓存局部性和整体性能。而线程池维护着多个线程，等待着监督管理
 * 者分配可并发执行的任务。这避免了在处理短时间任务时创建与销毁线程的代
 * 价。线程池不仅能够保证内核的充分利用，还能防止过分调度。
 *
 * 线程池的优势：
 * 线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，
 * 然后在线程创建后启动这些任务，如果线程数量超过了最大数量，
 * 超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行.
 *
 *
 * @author ChenCheng
 * @create 2022-06-14 21:40
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        // 一池多(5)线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        // 一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        // 一池可扩展
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        // 10个顾客请求
        try {

            for (int i = 1; i <=10; i++) {
                //执行
              //  threadPool1.execute(new Runnable() {
              //  threadPool2.execute(new Runnable() {
                threadPool3.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+" 办理业务");
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
          // 关闭线程池
            // threadPool1.shutdown();
            // threadPool2.shutdown();
             threadPool3.shutdown();


        }


    }
}
