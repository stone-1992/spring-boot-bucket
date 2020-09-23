package com.stone;

import com.stone.common.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ZhcxApiscanServerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ZhcxApiscanServerApplication.class, args);
        SpringContextUtils.setApplicationContext(applicationContext);
    }
}
