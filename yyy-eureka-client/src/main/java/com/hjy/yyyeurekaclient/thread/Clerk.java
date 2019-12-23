package com.hjy.yyyeurekaclient.thread;

public class Clerk {

    private int product = -1;

    //这个方法由生产者调用
    public synchronized void setProduct(int product) {
        if (this.product != -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.product = product;
        System.out.printf("生产者设定 %d)%n", this.product);
        notify();
    }

    //这个方法由消费者调用
    public synchronized int getProduct() {
        if (this.product == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int p = this.product;
        System.out.printf("消费者取走 (%d)%n",this.product);
        this.product = -1;
        notify();
        return p;
    }

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        new Thread(new ProductInt(clerk)).start();
        new Thread(new ConsumerInt(clerk)).start();
    }

}

class ProductInt implements Runnable {

    private Clerk clerk;

    public ProductInt(Clerk clerk) {
        this.clerk = clerk;
    }

    public void run() {
        System.out.println("生产者开始生产整数了..................");
        for (int product = 1; product <= 10; product++) {
            try {
                Thread.sleep((long) (Math.random() * 300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.setProduct(product);
        }
    }
}

class ConsumerInt implements Runnable{
    private Clerk clerk;
    public ConsumerInt(Clerk clerk){
        this.clerk = clerk;
    }
    public void run() {
        System.out.println("消费者开始消耗整数........");
        for (int i = 1; i <=10 ; i++) {
            try {
                Thread.sleep((int)Math.random()*300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.getProduct();//从店员取走整数
        }
    }

}
