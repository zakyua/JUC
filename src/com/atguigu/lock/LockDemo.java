package com.atguigu.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *    如果一个代码块被 synchronized 修饰了，当一个线程获取了对应的锁，并执
 *    行该代码块时，其他线程便只能一直等待，等待获取锁的线程释放锁，而这里
 *    获取锁的线程释放锁只会有两种情况：
 *    1）获取锁的线程执行完了该代码块，然后线程释放对锁的占有；
 *    2）线程执行发生异常，此时 JVM 会让线程自动释放锁。
 *      那么如果这个获取锁的线程由于要等待 IO 或者其他原因（比如调用 sleep
 *      方法）被阻塞了，但是又没有释放锁，其他线程便只能干巴巴地等待，试想一
 *      下，这多么影响程序执行效率。
 *      因此就需要有一种机制可以不让等待的线程一直无期限地等待下去（比如只等
 *      待一定的时间或者能够响应中断），通过 LockDemo 就可以办到。
 *
 *   1.LockDemo :
 *   LockDemo 锁实现提供了比使用同步方法和语句可以获得的更广泛的锁操作。它们允
 *   许更灵活的结构，可能具有非常不同的属性，并且可能支持多个关联的条件对
 *   象。LockDemo 提供了比 synchronized 更多的功能
 *
 *   2.LockDemo 与的 Synchronized 区别
 *   • LockDemo 不是 Java 语言内置的，synchronized 是 Java 语言的关键字，因此是内
 *          置特性。LockDemo 是一个接口，通过这个类可以实现同步访问；
 *   • LockDemo 和 synchronized 有一点非常大的不同，采用 synchronized 不需要用户
 *          去手动释放锁，当 synchronized 方法或者 synchronized 代码块执行完之后，
 *          系统会自动让线程释放对锁的占用；而 LockDemo 则必须要用户去手动释放锁，如
 *          果没有主动释放锁，就有可能导致出现死锁现象。
 *
 *  public interface LockDemo {
 *      void lock();
 *      void lockInterruptibly() throws InterruptedException;
 *      boolean tryLock();
 *      boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
 *      void unlock();
 *      Condition newCondition();
 * }
 *
 *
 * @author ChenCheng
 * @create 2022-06-14 16:30
 */
public class LockDemo {


    public static void main(String[] args) throws InterruptedException {

        // Lock中方法的一个使用
        Lock lock = new ReentrantLock();
        try {
            /*
              lock()方法是平常使用得最多的一个方法，就是用来获取锁。如果锁已被其他
              线程获取，则进行等待。
             */
            lock.lock();
            // 处理任务。。。。
        } finally {
            /*
             释放锁
             */
            lock.unlock();
        }


        /*
        newCondition()
        关键字 synchronized 与 wait()/notify()这两个方法一起使用可以实现等待/通
        知模式， LockDemo 锁的 newCondition()方法返回 Condition 对象，Condition 类
        也可以实现等待/通知模式。
        用 notify()通知时，JVM 会随机唤醒某个等待的线程， 使用 Condition 类可以
        进行选择性通知， Condition 比较常用的两个方法
        • await()会使当前线程等待,同时会释放锁,当其他线程调用 signal()时,线程会重
        新获得锁并继续执行。
        • signal()用于唤醒一个等待的线程。

         */
        Condition condition = lock.newCondition();
        // 类似wait()
        condition.await();
        // 类似notifyAll()
        condition.signalAll();


    }




}
