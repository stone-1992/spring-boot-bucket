package com.stone.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import sun.plugin2.message.JVMStartedMessage;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/10/13 17:45
 */
@Component
@Slf4j
public class Producer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    public void sendMsg(String msg) {
        log.info("发送消息：{}", msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
