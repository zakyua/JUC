package com.atguigu.lock;

import javax.crypto.interfaces.PBEKey;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChenCheng
 * @create 2022-06-14 17:14
 */

// 资源类
class Share{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // +1
    public void incr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            // 通知其他线程
            condition.signalAll();
        } finally {
            // 释放锁
            lock.unlock();
        }

    }

    // -1
    public void decr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (number != 1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo2 {


    public static void main(String[] args) {
        Share share = new Share();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10 ; i++) {
                    try {
                        share.incr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10 ; i++) {
                    try {
                        share.decr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10 ; i++) {
                    try {
                        share.incr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"CC").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10 ; i++) {
                    try {
                        share.decr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"DD").start();

    }
}
