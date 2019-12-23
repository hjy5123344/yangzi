package com.hjy.yyyeurekaclient.thread;

public class Tortoise extends Animal {

    public Tortoise() {
        setName("乌龟");
    }

    @Override
    public void running() {
        double dis = 0.1;
        length -= dis;

        if (length <= 0) {
            length =0;
            System.out.println("wugui win");
            if (calltoback != null) {
                calltoback.win();
            }
        }

        System.out.println("乌龟跑了" + dis + "米，距离终点还有" + (int) length + "米");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
