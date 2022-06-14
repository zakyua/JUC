package com.atguigu.readwrite;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 读写锁：ReentrantReadWriteLock
 * 现实中有这样一种场景：对共享资源有读和写的操作，且写操作没有读操作那
 * 么频繁。在没有写操作的时候，多个线程同时读一个资源没有任何问题，所以
 * 应该允许多个线程同时读取共享资源；但是如果一个线程想去写这些共享资源，
 * 就不应该允许其他线程对该资源进行读和写的操作了。
 * 针对这种场景，JAVA 的并发包提供了读写锁 ReentrantReadWriteLock，
 * 它表示两个锁，一个是读操作相关的锁，称为共享锁；一个是写相关的锁，称
 * 为排他锁
 *
 * 1. 线程进入读锁的前提条件：
 * • 没有其他线程的写锁
 * • 没有写请求, 或者有写请求，但调用线程和持有锁的线程是同一个(可重入锁)。
 * 2. 线程进入写锁的前提条件：
 * • 没有其他线程的读锁
 * • 没有其他线程的写锁
 *
 * 而读写锁有以下三个重要的特性：
 * （1）公平选择性：支持非公平（默认）和公平的锁获取方式，吞吐量还是非公平优于公平。
 * （2）重进入：读锁和写锁都支持线程重进入。
 * （3）锁降级：遵循获取写锁、获取读锁再释放写锁的次序，写锁能够降级成为读锁。
 *
 *
 * 场景: 使用 ReentrantReadWriteLock 对一个 hashmap 进行读和写操作
 * @author ChenCheng
 * @create 2022-06-14 20:04
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {

        MyCache myCache = new MyCache();
        //创建线程放数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.put(num+"",num+"");
                }
            },String.valueOf(i)).start();
        }


        TimeUnit.MICROSECONDS.sleep(300);

        // 取数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.put(num+"",num+"");
                }
            },String.valueOf(i)).start();
        }

    }

}


// 资源类
class MyCache{
       //创建map集合
       private volatile Map<String,Object> map = new HashMap<>();
        //创建读写锁对象
       private ReadWriteLock rwLock = new ReentrantReadWriteLock();

       // 向map中放数据(写)
       public void put(String key,Object value) {
           // 添加写锁
           rwLock.writeLock().lock();
           try {
               System.out.println(Thread.currentThread().getName()+" 正在写操作"+key);
               //暂停一会
               TimeUnit.MICROSECONDS.sleep(300);
               // 向map中放数据
               map.put(key,value);
               System.out.println(Thread.currentThread().getName()+" 写完了"+key);
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               // 释放写锁
               rwLock.writeLock().unlock();
           }

       }

       // 从map中取数据
       public Object get(String key) {
           // 添加读锁
           rwLock.readLock().lock();
           // 准备要返回的数据
           Object result = null;
           try {
               System.out.println(Thread.currentThread().getName()+" 正在读操作"+key);
               //暂停一会
               TimeUnit.MICROSECONDS.sleep(300);
               // 取数据
               result = map.get(key);
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               // 释放锁
               rwLock.readLock().unlock();
           }
           return result;
       }



}












