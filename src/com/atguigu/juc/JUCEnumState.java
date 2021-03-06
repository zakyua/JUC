package com.atguigu.juc;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

/**
 * 1.
 * 线程的状态枚举类
 * public enum State {
 *  NEW,             创建线程
 *  RUNNABLE,        准备就绪
 *  BLOCKED,         阻塞
 *  WAITING,         不见不散(运行态)
 *  TIMED_WAITING,   过时不候(运行态)
 *  TERMINATED;      终结
 *  }
 *
 *  2.  wait/sleep 的区别
 *      (1) sleep 是 Thread 的静态方法，wait 是 Object 的方法，任何对象实例都能调用
 *      (2) sleep 不会释放锁，它也不需要占用锁。wait 会释放锁，但调用它的前提是当前线程占有锁(即代码要在 synchronized 中)。
 *      (3) 它们都可以被 interrupted 方法中断
 *
 * 3. 并行和并发    并发(同一时刻多个线程在访问同一个资源)  并行(多项工作一起执行，之后再汇总)
 *    (1) 串行表示所有任务都一一按先后顺序进行。串行意味着必须先装完一车柴才能
 *        运送这车柴，只有运送到了，才能卸下这车柴，并且只有完成了这整个三个步
 *        骤，才能进行下一个步骤。串行是一次只能取得一个任务，并执行这个任务。
 *    (2) 并行意味着可以同时取得多个任务，并同时去执行所取得的这些任务。并行模
 *        式相当于将长长的一条队列，划分成了多条短队列，所以并行缩短了任务队列的长度。
 *        并行的效率从代码层次上强依赖于多进程/多线程代码，从硬件角度上
 *        则依赖于多核 CPU。
 *    (3) 并发(concurrent)指的是多个程序可以同时运行的现象，更细化的是多进程可
 *        以同时运行或者多指令可以同时运行。但这不是重点，在描述并发的时候也不
 *        会去扣这种字眼是否精确，==并发的重点在于它是一种现象==, ==并发描述
 *        的是多进程同时运行的现象==。但实际上，对于单核心 CPU 来说，同一时刻
 *        只能运行一个线程。所以，这里的"同时运行"表示的不是真的同一时刻有多个
 *        线程运行的现象，这是并行的概念，而是提供一种功能让用户看来多个程序同
 *        时运行起来了，但实际上这些程序中的进程不是一直霸占 CPU 的，而是执行一
 *        会停一会。
 *        要解决大并发问题，通常是将大任务分解成多个小任务, 由于操作系统对进程的调度是随机的，
 *        所以切分成多个小任务后，可能会从任一小任务处执行。这可
 *        能会出现一些现象：
 *        • 可能出现一个小任务执行了多次，还没开始下个任务的情况。这时一般会采用
 *          队列或类似的数据结构来存放各个小任务的成果
 *        • 可能出现还没准备好第一步就执行第二步的可能。这时，一般采用多路复用或
 *          异步的方式，比如只有准备好产生了事件通知才执行某个任务。
 *        • 可以多进程/多线程的方式并行执行这些小任务。也可以单进程/单线程执行这
 *          些小任务，这时很可能要配合多路复用才能达到较高的效率
 *
 *  4.管程(monitor)
 *      管程(monitor)是保证了同一时刻只有一个进程在管程内活动,即管程内定义的操作在同
 *      一时刻只被一个进程调用(由编译器实现).但是这样并不能保证进程以设计的顺序执行
 *      JVM 中同步是基于进入和退出管程(monitor)对象实现的，每个对象都会有一个管程
 *      (monitor)对象，管程(monitor)会随着 java 对象一同创建和销毁
 *      执行线程首先要持有管程对象，然后才能执行方法，当方法完成之后会释放管程，方
 *      法在执行时候会持有管程，其他线程无法再获取同一个管程
 *
 *  5.用户线程和守护线程
 *    用户线程:平时用到的普通线程,自定义线程
 *    守护线程:运行在后台,是一种特殊的线程,比如垃圾回收
 *    当主线程结束后,用户线程还在运行,JVM 存活
 *    如果没有用户线程,都是守护线程,JVM 结束
 *
 *
 *
 * @author ChenCheng
 * @create 2022-06-14 15:53
 */
public class JUCEnumState {

    public static void main(String[] args){





    }



}
