package com.crowdfunding.thread;


/**
 * 方法1：继承Thread类  重写  run方法
 */
public class T1 extends Thread {


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("run方法执行了"+i);
        }

    }


    public synchronized static void main(String[] args) {
        T1 t1 = new T1();


        t1.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main线程体执行了"+i);
        }
    }
}
