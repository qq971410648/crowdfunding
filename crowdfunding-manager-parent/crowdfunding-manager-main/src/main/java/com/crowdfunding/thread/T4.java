package com.crowdfunding.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T4 implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("run方法执行了"+i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        T4 t4 = new T4();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(t4);

        Thread.sleep(5000);

        executorService.execute(t4);

        executorService.shutdown();
    }
}
