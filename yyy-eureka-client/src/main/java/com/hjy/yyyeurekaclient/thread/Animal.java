package com.hjy.yyyeurekaclient.thread;

public abstract class Animal extends Thread {

    public static void main(String[] agrs) {
        Tortoise tortoise = new Tortoise();
        Rabbit rabbit = new Rabbit();
        LetOneStop letOneStop1 = new LetOneStop(tortoise);
        rabbit.calltoback = letOneStop1;//让兔子的回调方法里面存在乌龟对象的值，可以把乌龟stop
        LetOneStop letOneStop2 = new LetOneStop(rabbit);
        tortoise.calltoback = letOneStop2;
        tortoise.start();
        rabbit.start();
    }

    public double length = 20; //比赛长度

    public abstract void running();//抽象方法需要子类实现

    @Override
    public void run() {
        super.run();
        while (length > 0) {
            running();
        }
    }

    public interface Calltoback {
        void win();
    }

    public Calltoback calltoback;
}
