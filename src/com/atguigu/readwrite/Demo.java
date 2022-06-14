package com.atguigu.readwrite;

import sun.awt.geom.AreaOp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 演示写锁的降级
 *
 * 结论：
 * • 在线程持有读锁的情况下，该线程不能取得写锁(因为获取写锁的时候，如果发
 * 现当前的读锁被占用，就马上获取失败，不管读锁是不是被当前线程持有)。
 * • 在线程持有写锁的情况下，该线程可以继续获取读锁（获取读锁时如果发现写
 * 锁被占用，只有写锁没有被当前线程占用的情况才会获取失败）。
 * 原因: 当线程获取读锁的时候，可能有其他线程同时也在持有读锁，因此不能把
 * 获取读锁的线程“升级”为写锁；而对于获得写锁的线程，它一定独占了读写
 * 锁，因此可以继续让它获取读锁，当它同时获取了写锁和读锁后，还可以先释
 * 放写锁继续持有读锁，这样一个写锁就“降级”为了读锁。
 *
 *
 *
 * @author ChenCheng
 * @create 2022-06-14 20:57
 */
public class Demo {

    public static void main(String[] args) {
        // 获取读写锁
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        // 获取读锁
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        // 获取写锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

/*
        // 上写锁
        writeLock.lock();

        // 在上读锁
        readLock.lock();
        System.out.println("atguigu");

        // 释放读锁
        readLock.unlock();

        // 释放写锁
        writeLock.unlock();
*/

        // 上读锁
        readLock.lock();
        System.out.println("---read");

        // 上写锁
        writeLock.lock();
        System.out.println("atguigu");
        // 释放读锁
        writeLock.unlock();

        // 释放读锁
        readLock.unlock();


    }


}
