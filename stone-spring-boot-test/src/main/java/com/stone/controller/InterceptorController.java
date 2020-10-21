package com.stone.controller;

import com.stone.config.MyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/28 14:43
 */
@RestController
@RequestMapping(value = "interceptor")
@Slf4j
public class InterceptorController {

    @Autowired
    private MyProperties properties;

    @GetMapping("test")
    public String interceptorTest() {
        log.info("interceptorTest................");
        log.info("list : {}", properties.getList());
        log.info("logInfo : {}", properties.getLogInfo());
        log.info("enabled : {}", properties.isEnabled());
        return "true";
    }
}
