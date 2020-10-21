package com.stone.controller;

import com.stone.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/10/13 17:49
 */
@RestController
public class JmsTestController {

    @Autowired
    private Producer producer;

    @GetMapping(value = "sendMsg")
    public String testMsg(@RequestParam(value = "msg") String msg) {
        producer.sendMsg(msg);
        return "操作成功";
    }
}
