package com.hjy.yyyeurekaclient.thread;

public class Bank {

    public static int money;

    public int getMoney() {
        return money;
    }

    public void saveMoney(int m) {
        synchronized (this) {
            System.out.println(("存钱后的总金额：" + (money += m)));
        }
    }

    public void drawMoney(int m) {
        synchronized (this) {
            Bank bank = new Bank();
            if (bank.getMoney() <= 0) {
                System.out.println("没钱了");
            } else {
                System.out.println("取钱后剩的总金额："+(money-=m));
            }
        }
    }

    public static void main(String[] args) {
        Man m1 = new Man();
        Woman w = new Woman();
        Thread t1 = new Thread(m1);
        Thread t2 = new Thread(m1);
        Thread t3 = new Thread(m1);
        Thread t4 = new Thread(w);
        Thread t5 = new Thread(w);
        Thread t6 = new Thread(w);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}

class Man implements Runnable {

    private Bank bank = new Bank();

    public void run() {
        int m = 100;
        int i = 0;
        while (i < 5) {
            bank.saveMoney(m);
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Woman implements Runnable {

    private Bank bank = new Bank();

    public void run() {
        int m = 100;
        int i = 0;
        while (i < 5) {
            bank.drawMoney(m);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
