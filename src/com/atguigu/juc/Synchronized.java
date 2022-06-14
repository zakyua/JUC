package com.atguigu.juc;

/**
 *
 *  synchronized 是 Java 中的关键字，是一种同步锁。它修饰的对象有以下几种：
 *      1. 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}
 *          括起来的代码，作用的对象是调用这个代码块的对象；
 *      2. 修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用
 *          的对象是调用这个方法的对象；
 *          。 虽然可以使用 synchronized 来定义方法，但 synchronized 并不属于方法定
 *                  义的一部分，因此，synchronized 关键字不能被继承。如果在父类中的某个方
 *                  法使用了 synchronized 关键字，而在子类中覆盖了这个方法，在子类中的这
 *                  个方法默认情况下并不是同步的，而必须显式地在子类的这个方法中加上
 *                  synchronized 关键字才可以。当然，还可以在子类方法中调用父类中相应的方
 *                  法，这样虽然子类中的方法不是同步的，但子类调用了父类的同步方法，因此，
 *                  子类的方法也就相当于同步了。
 *      3.修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的
 *          所有对象；
 *      4.修改一个类，其作用的范围是 synchronized 后面括号括起来的部分，作用主
 *          的对象是这个类的所有对象。
 *
 *
 * @author ChenCheng
 * @create 2022-06-14 16:31
 */
public class Synchronized {

    public static void main(String[] args) {
        // 创建多个线程，调用资源类的操作方法
        // 创建一个资源类，从头到位多个线程操作的资源(票数)都是这一份
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();

    }


}

// 售票案例
// 创建资源类，定义属性和和操作方法
class Ticket {

    // 票数
    private int number = 30;
    // 操作方法：卖票
    public synchronized void sale(){
        // 判断是否有票
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+" : "+(number--)+" "+number);
        }
    }

}
