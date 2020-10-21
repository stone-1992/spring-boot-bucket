package com.stone.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/10/13 17:48
 */
@Component
@Slf4j
public class Consumer {

    @JmsListener(destination = "sms.queue")
    public void receiveMsg(String text) {
        log.info("接收到的消息：{}", text);
    }
}
