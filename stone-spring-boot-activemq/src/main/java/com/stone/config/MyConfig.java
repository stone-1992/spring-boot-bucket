package com.stone.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/10/13 17:42
 */
@Configuration
public class MyConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sms.queue");
    }

}
