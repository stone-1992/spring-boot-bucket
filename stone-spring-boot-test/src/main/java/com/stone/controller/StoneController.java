package com.stone.controller;

import com.stone.face.AspectLog;
import com.stone.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 测试 stone-spring-boot-starter
 * @date 2020/9/18 15:01
 */
@Slf4j
@RestController
public class StoneController {

    @Autowired
    ExampleService exampleService;

    @Autowired
    private Executor asyncExecutor;

    @GetMapping(value = "asyncExecutor")
    public String asyncExecutor() {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
                log.info("我休眠了 1 秒钟");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "我休眠了";
        }, asyncExecutor);
        return "true";
    }

    @GetMapping(value = "test")
    public String test() {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.err.println("休眠了1秒............");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "true";
    }

    @GetMapping(value = "aspectLogDemo")
    @AspectLog(desc = "日志记录测试接口")
    public String aspectLogDemo() {
        String hello = exampleService.wrap("hello");
        return "aspectLogDemo : " + hello;
    }
}
