package com.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/10/13 17:52
 */
@SpringBootApplication
@EnableJms
public class ActiveMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplication.class, args);
    }
}
