package com.hjy.yyyeurekaclient.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTicket {

    public static void main(String[] args) {
        Booking b1 = new Booking("军人售票口");
        Booking b2 = new Booking("学生售票口");
        Booking b3 = new Booking("老年人售票口");
        Booking b4 = new Booking("网上售票口");
        b1.start();
        b2.start();
        b3.start();
        b4.start();
    }


}

/*
 * 多窗口卖票系统。多线程
 * 票数为静态的，共享数据
 * synchronized(对象){}代码块中的内容是加锁的，
 * 即当一个线程在使用时，其他线程不可以进入。
 * 使得共享资源数据的安全。
 */
class Booking extends Thread {

    public Booking(String name) {
        super(name);
    }

    static int ticket = 50; //票数50涨
    Lock lock = new ReentrantLock(false);//明锁


    /* Lock是个接口，只能实例化它的子类
     * 明锁适合高并发，上万
     * 暗锁适合并发率不高时，效率高
     */
    public void run() {
        while (ticket > 0) {
            synchronized (Booking.class) {
                if (ticket > 0) {
                    System.out.println(super.getName()+"窗口---->卖出的车票号No."+ticket);
                    ticket--;
                } else {
                    System.out.println(super.getName()+"票已售罄！！!");
                }
                try {
                    sleep(100);//睡100毫秒，抛出多线程异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
              /* lock.lock();//加锁，锁定以下代码
                    if (ticket>0) {
                        System.out.println(super.getName()+"卖票："+ticket);
                        ticket--;
                    }else {
                        System.out.println(super.getName()+"票已售罄！！！");
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();//解锁*/
        }
    }
}
