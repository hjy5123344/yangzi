package com.hjy.yyyeurekaclient.thread;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    public static void main(String[] args) {
        ThreadLock tl = new ThreadLock(true);
        ThreadLock tl2 = new ThreadLock(false);
        new Thread(tl).start();
        new Thread(tl2).start();
    }
}

/*
 * 死锁的产生条件：
 * 1、至少一个资源共享
 * 2、至少有一个线程（任务），必须持有资源，且等待获取别的线程持有的资源
 * 3、任务抢不到资源
 * 4、必须有无限循环
 * （1） 互斥条件：一个资源每次只能被一个进程使用。
 * （2） 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * （3） 不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
 * （4） 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 * 举例说明：不可剥夺资源A、B，进程C、D
 * 不可剥夺资源：一个进程申请了之后，不能强制收回，只能进程结束之后自动释放。内存是可剥夺资源
 * 进程C申请了资源A，进程D申请了资源B。
 * 接下来进程C的操作需要用到资源B，进程D的操作需要用到资源A
 * 但是C、D都得不到资源，就引发了死锁
 */

class Lock{
    static Object lockOne = new Object();//资源A
    static Object lockTwo = new Object();//资源B
}


class ThreadLock implements Runnable{
    private boolean flag;

    java.util.concurrent.locks.Lock lock = new ReentrantLock(false);//明锁
    public ThreadLock(boolean flag){
        this.flag = flag;
    }

    public void run() {
        if(flag){
            while (true) {
                synchronized (Lock.lockOne) {
                    System.out.println(" this is lockOne");
                    synchronized (Lock.lockTwo) {
                        System.out.println("this is lockTwo");
                    }
                }
            }
        }else {
            while (true) {
                lock.lock();
                //synchronized (Lock.lockTwo) {
                    System.out.println(" 这是 lockTwo");
                    //synchronized (Lock.lockOne) {
                        System.out.println("这是 lockOne");
                    //}
                lock.unlock();
                //}
            }
        }
    }


}