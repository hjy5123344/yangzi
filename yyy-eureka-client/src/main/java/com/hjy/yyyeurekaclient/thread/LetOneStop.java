package com.hjy.yyyeurekaclient.thread;

public class LetOneStop implements Animal.Calltoback {

    Animal animal;

    // 获取动物对象，可以传入兔子或乌龟的实例
    public LetOneStop(Animal animal) {
        this.animal = animal;
    }

    public void win() {
        animal.stop ();
    }
}
