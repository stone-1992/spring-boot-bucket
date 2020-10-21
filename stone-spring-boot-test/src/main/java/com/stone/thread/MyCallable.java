package com.stone.thread;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/25 14:42
 */
public class MyCallable implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        System.err.println("我是 Callable 启动的线程 : " + Thread.currentThread().getName());
        // 休眠两秒
        TimeUnit.SECONDS.sleep(2L);
        return 111;

    }

    public static void main(String[] args) throws Exception {
        String name = null;

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("name is null");
            }
            return "hello " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger !");

        // future.get() = Hello, Stranger !
        System.err.println(future.get());

    }
}
