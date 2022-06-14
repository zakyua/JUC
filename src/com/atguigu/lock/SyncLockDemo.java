package com.atguigu.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author ChenCheng
 * @create 2022-06-14 18:42
 */
public class SyncLockDemo {



    public static void main(String[] args) {
//        //Lock演示可重入锁
//        Lock lock = new ReentrantLock();
//        //创建线程
//        new Thread(()->{
//            try {
//                //上锁
//                lock.lock();
//                System.out.println(Thread.currentThread().getName()+" 外层");
//
//                try {
//                    //上锁
//                    lock.lock();
//                    System.out.println(Thread.currentThread().getName()+" 内层");
//                }finally {
//                    //释放锁
//                    lock.unlock();
//                }
//            }finally {
//                //释放做
//                lock.unlock();
//            }
//        },"t1").start();



         //synchronized
        Object o = new Object();
        new Thread(()->{
            synchronized(o) {
                System.out.println(Thread.currentThread().getName()+" 外层");

                synchronized (o) {
                    System.out.println(Thread.currentThread().getName()+" 中层");

                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName()+" 内层");
                    }
                }
            }

        },"t1").start();

    }
}
