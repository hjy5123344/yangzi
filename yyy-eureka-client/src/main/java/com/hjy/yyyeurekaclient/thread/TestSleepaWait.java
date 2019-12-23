package com.hjy.yyyeurekaclient.thread;

public class TestSleepaWait {

    public static void main(String[] args) {

        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();

    }
}

class Thread1 implements Runnable {
    public void run() {
        synchronized (TestSleepaWait.class) {
            System.out.println("Thread1 is start........");
            System.out.println("Thread1 is wait..............");
            try {
                //调用wait方法，线程会放弃对象锁，进入等待对象的等待锁定池
                TestSleepaWait.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 is go on........");
            System.out.println("Thread1 is over!");
        }
    }
}

class Thread2 implements Runnable {

    public void run() {
        synchronized (TestSleepaWait.class) {
            System.out.println("Thread2 is enter..........");
            System.out.println("Thread2 is sleep.......");
            //只有针对对象调用notify()方法后本线程才进入对象锁定池
            //准备获取对象进入运行状态
            TestSleepaWait.class.notify();
            //如果把上句注释掉。即对象锁调用了wait方法，但是没有调用notify
            //程序就一致处于挂起状态
            try {
                Thread.sleep(5000);
                //sleep方法暂停执行时间，让出CPU，监控状态保持，
                //时间到 了就回复运行， 不会释放对象锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 is going on..........");
            System.out.println("Thread2 is over!!!!");
        }
    }
}
