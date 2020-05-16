package com.crowdfunding.thread;

/**
 * 方法1：实现Runnable 重写  run方法
 */
public class T2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("run方法执行了"+i);
        }
    }


    public static void main(String[] args) {

        T2 t2 = new T2();
        Thread thread = new Thread(t2);
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main线程执行了"+i);
        }

    }
}
