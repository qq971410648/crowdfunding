package com.crowdfunding.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现callable接口   重写call方法    该方法有返回值
 */
public class T3 implements Callable {


    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println("call方法执行了"+i);
        }
        return null;
    }


    public static void main(String[] args) {
        T3 t3 = new T3();
        FutureTask task = new FutureTask<>(t3);
        Thread thread = new Thread(task);
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main线程体执行了"+i);
        }
    }
}
