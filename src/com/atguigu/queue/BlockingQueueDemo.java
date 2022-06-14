package com.atguigu.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 阻塞队列，顾名思义，首先它是一个队列, 通过一个共享的队列，可以使得数据
 * 由队列的一端输入，从另外一端输出；
 *
 * 当队列是空的，从队列中获取元素的操作将会被阻塞
 * 当队列是满的，从队列中添加元素的操作将会被阻塞
 * 试图从空的队列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入新的元素
 * 试图向已满的队列中添加新元素的线程将会被阻塞，直到其他线程从队列中移除一个或多
 * 个元素或者完全清空，使队列变得空闲起来并后续新增
 *
 * 常用的队列主要有以下两种：
 * • 先进先出（FIFO）：先插入的队列的元素也最先出队列，类似于排队的功能。从某种程度上来说这种队列也体现了一种公平性
 * • 后进先出（LIFO）：后插入队列的元素最先出队列，这种队列优先处理最近发生的事件(栈)
 *
 *
 * • 当队列中没有数据的情况下，消费者端的所有线程都会被自动阻塞（挂起），直到有数据放入队列
 * • 当队列中填满数据的情况下，生产者端的所有线程都会被自动阻塞（挂起），直到队列中有空的位置，线程被自动唤醒
 *
 * @author ChenCheng
 * @create 2022-06-14 21:07
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // 获取一个阻塞队列 定义长度为3
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /*
        第一组方法: 抛出异常
        插入 add()
        移除 remove()
        检查 element()
         */
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//
        /*
        取出队头的数据
         System.out.println(blockingQueue.element());
         */

        /*
        定义阻塞队列的长度为3，使用add向其中添加第四个元素报异常
        System.out.println(blockingQueue.add("d"));
         */
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
        /*
        定义阻塞队列的长度为3，使用remove向其中移除第四个元素报异常
        System.out.println(blockingQueue.remove());
         */




          /*
        第二组方法:  特殊值
        插入 offer()
        移除 poll()
        检查 peek()
         */
//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
        // System.out.println(blockingQueue.peek());
        /*
        定义阻塞队列的长度为3，使用offer向其中添加第四个元素插入失败
        System.out.println(blockingQueue.offer("d"));
         */
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
        /*
        定义阻塞队列的长度为3，使用poll向其中移除第四个元素移除失败
        System.out.println(blockingQueue.poll());
         */




          /*
        第三组方法: 阻塞
        插入 put()
        移除 take()
        检查 不支持
         */
//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
        /*
        定义阻塞队列的长度为3，使用put向其中添加第四个元素陷入阻塞
         blockingQueue.put("d");
         */
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
        /*
        定义阻塞队列的长度为3，使用take向其中移除第四个元素陷入阻塞
         System.out.println(blockingQueue.take());
         */



          /*
        第四组方法: 超时
        插入 offer() 插入数据,超出阻塞队列的长度，然后指定时间后退出
        移除 poll()
        检查
         */

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("w",3L, TimeUnit.SECONDS));


    }
}
