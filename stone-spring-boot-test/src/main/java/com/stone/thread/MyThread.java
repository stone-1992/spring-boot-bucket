package com.stone.thread;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/25 14:30
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.err.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // 启动线程
        MyThread thread = new MyThread();
        thread.start();
    }
}
