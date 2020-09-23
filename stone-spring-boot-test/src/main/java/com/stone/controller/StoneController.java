package com.stone.controller;

import com.stone.face.AspectLog;
import com.stone.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 测试 stone-spring-boot-starter
 * @date 2020/9/18 15:01
 */
@RestController
public class StoneController {

    @Autowired
    ExampleService exampleService;

    @GetMapping(value = "test")
    @AspectLog(desc = "日志记录 test 接口")
    public String test() {
        String hello = exampleService.wrap("hello");
        return "test : " + hello;
    }
}
