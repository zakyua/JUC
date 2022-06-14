package com.atguigu.lock;

import org.junit.experimental.theories.Theories;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ChenCheng
 * @create 2022-06-14 17:35
 */
/*
   list集合线程不安全
   解决方式:
           1.Vector
           2.Collections
           3.CopyOnWriteArrayList
   重点CopyOnWriteArrayList
       它相当于线程安全的 ArrayList。和 ArrayList 一样，它是个可变数组；但是和ArrayList 不同的时，它具有以下特性：
       1.它最适合于具有以下特征的应用程序：List 大小通常保持很小，只读操作远多于可变操作，需要在遍历期间防止线程间的冲突。
       2.它是线程安全的。
       3.因为通常需要复制整个基础数组，所以可变操作（add()、set() 和 remove()等等）的开销很大。
       4.迭代器支持 hasNext(), next()等不可变操作，但不支持可变 remove()等操作。
       5.使用迭代器进行遍历的速度很快，并且不会与其他线程发生冲突。在构造迭代器时，迭代器依赖于不变的数组快照。

            1. 独占锁效率低：采用读写分离思想解决
            2. 写线程获取到锁，其他写线程阻塞
            3. 复制思想：
            当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容
            器进行 Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素
            之后，再将原容器的引用指向新的容器。
            这时候会抛出来一个新的问题，也就是数据不一致的问题。如果写线程还没来得及写会内存，其他的线程就会读到了脏数据。



 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        // 创建ArrayList
        // ArrayList<String> list = new ArrayList<>();

        /*
         方式一：使用Vector解决
        List<String> list = new Vector<>();
         */


        /*
        方式二：使用Collections
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
         */

        /*
        方式三：使用CopyOnWriteArrayList解决 读写锁
         */
/*        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 向集合中加入元素
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    // 从集合中取出元素
                    System.out.println(list);
                }
            },String.valueOf(i)).start();
        }*/


        // 演示HashMap
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30 ; i++) {
            // 准备map的键
            String key = String.valueOf(i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //向集合添加内容
                    map.put(key,UUID.randomUUID().toString().substring(0,8));
                    //从集合获取内容
                    System.out.println(map);
                }
            },String.valueOf(i)).start();




        }


    }
}
