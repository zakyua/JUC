package com.atguigu.juc;

/**
 * @author ChenCheng
 * @create 2022-06-14 17:02
 */


//第一步 创建资源类，定义属性和操作方法
class Share{
    //初始值
    private int number = 0;
    // +1的方法
    public synchronized void incr() throws InterruptedException {
        // 判断
        if(number != 0){
            // 判断number值是否是0，如果不是0，等待
            this.wait();
            /*
             在哪里睡，就在哪里醒
             当别的线程将他唤醒，他会直接执行下面的方法，就会++,导致最后这个number越来越大
             使用lock解决
             */
        }
        // 如果number值是0，就+1操作
        number++;
        System.out.println(Thread.currentThread().getName()+" :: "+number);
        // 通知其他的线程
        //通知其他线程
        this.notifyAll();
    }

    // -1的方法
    public synchronized void decr() throws InterruptedException {
        // 判断
        if(number != 1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+" :: "+number);
        //通知其他线程
        this.notifyAll();
    }
}
public class ThreadDemo1 {

    public static void main(String[] args) {
        Share share = new Share();
        // 创建多个线程，调用资源类的操作方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        share.incr(); // +1
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        share.decr(); // -1
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"BB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        share.incr(); // +1
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"CC").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        share.decr(); // -1
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"DD").start();




    }


}
