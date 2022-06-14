package com.atguigu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *  Callable接口
 * @author ChenCheng
 * @create 2022-06-14 18:48
 */
/*
1.Callable 接口的特点如下(重点)
• 为了实现 Runnable，需要实现不返回任何内容的 run（）方法，而对于
Callable，需要实现在完成时返回结果的 call（）方法。
• call（）方法可以引发异常，而 run（）则不能。
• 为实现 Callable 而必须重写 call 方法
• 不能直接替换 runnable,因为 Thread 类的构造方法根本没有 Callable


2.Future 接口
当 call（）方法完成时，结果必须存储在主线程已知的对象中，以便主线程可
以知道该线程返回的结果。为此，可以使用 Future 对象。
将 Future 视为保存结果的对象–它可能暂时不保存结果，但将来会保存（一旦
Callable 返回）。Future 基本上是主线程可以跟踪进度以及其他线程的结果的
一种方式。要实现此接口，必须重写 5 种方法，这里列出了重要的方法,如下:


3.FutureTask
核心原理:(重点)
在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些
作业交给 Future 对象在后台完成
• 当主线程将来需要时，就可以通过 Future 对象获得后台作业的计算结果或者执行状态
• 一般 FutureTask 多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
• 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法
• 一旦计算完成，就不能再重新开始或取消计算
• get 方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，然后会返回结果或者抛出异常
• get 只计算一次,因此 get 方法放到最后

 */

public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        // 创建线程
        new Thread(futureTask,"AA").start();
        // 获取call方法中的返回值
        System.out.println(futureTask.get());
    }


}


// 创建新类 MyThread 实现 runnable 接口
class MyThread implements Runnable{
    @Override
    public void run() {

    }
}

// 新类 MyThread2 实现 callable 接口
class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
