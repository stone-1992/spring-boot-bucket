package com.stone.thread;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/25 14:38
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.err.println("线程启动：" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();
    }
}
