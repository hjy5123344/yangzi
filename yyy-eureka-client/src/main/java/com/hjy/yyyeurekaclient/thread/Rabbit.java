package com.hjy.yyyeurekaclient.thread;

public class Rabbit  extends Animal {

    public Rabbit() {
        setName("兔子");
    }


    @Override
    public void running() {
        double dis = 0.5;
        length -= dis;
        if (length <= 0) {
            length = 0;
            System.out.println("tuzi win");
            //回调
            if (calltoback != null) {
                calltoback.win();
            }
        }
        System.out.println("兔子跑了" + dis + "米，距离终点还有" + (int)length + "米");

        // 两米休息一次
        if (length % 2 == 0) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
