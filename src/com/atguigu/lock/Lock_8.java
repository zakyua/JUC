package com.atguigu.lock;

import java.util.concurrent.TimeUnit;

/**
 * 多线程锁
 * @author ChenCheng
 * @create 2022-06-14 18:29
 */

/*
1 标准访问，先打印短信还是邮件
------sendSMS
------sendEmail

2 停 4 秒在短信方法内，先打印短信还是邮件
------sendSMS
------sendEmail

3.新增普通的 hello 方法，是先打短信还是 hello
------getHello
------sendSMS

4.现在有两部手机，先打印短信还是邮件
------sendEmail
------sendSMS

5.两个静态同步方法，1 部手机，先打印短信还是邮件
------sendSMS
------sendEmail

6.两个静态同步方法，2 部手机，先打印短信还是邮件
------sendSMS
------sendEmail

7.1 个静态同步方法,1 个普通同步方法，1 部手机，先打印短信还是邮件
------sendEmail
------sendSMS

8.1 个静态同步方法,1 个普通同步方法，2 部手机，先打印短信还是邮件
------sendSMS
------sendEmail

结论：
一个对象里面如果有多个 synchronized 方法，某一个时刻内，只要一个线程去调用其中的一个 synchronized 方法了，
其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized 方法
锁的是当前对象 this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized 方法
加个普通方法后发现和同步锁无关
换成两个对象后，不是同一把锁了，情况立刻变化。
synchronized 实现同步的基础：Java 中的每一个对象都可以作为锁。

 */

public class Lock_8 {

    public static void main(String[] args) throws InterruptedException {

        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);
        new Thread(() -> {

            try {
                 phone.sendEmail();
                 //phone.getHello();
                //phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();


    }

}


class Phone{
    /**
     * 发短信
     */
    public static   synchronized void sendSMS() throws Exception {
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    /**
     * 发邮件
     */
    public  synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    /**
     * 打招呼
     */
    public void getHello() {
        System.out.println("------getHello");
    }
}
