package com.atguigu.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * 信号灯 Semaphore
 *  Semaphore 的构造方法中传入的第一个参数是最大信号量（可以看成最大线
 * 程池），每个信号量初始化为一个最多只能分发一个许可证。使用 acquire 方
 * 法获得许可证，release 方法释放许可
 *
 * 场景: 抢车位, 6 部汽车 3 个停车位
 * @author ChenCheng
 * @create 2022-06-14 19:55
 */
public class SemaphoreDemo {


    public static void main(String[] args) {
        //创建Semaphore，设置许可数量
        Semaphore semaphore = new Semaphore(3);

        // 模拟6辆汽车
        for (int i = 1; i <= 6; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 抢占许可证
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " 抢到了车位");
                        // 设置离开的时间
                        TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                        System.out.println(Thread.currentThread().getName() + " ------离开了车位");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 释放许可证
                        semaphore.release();
                    }
                }
            }, String.valueOf(i)).start();

        }


    }

}
