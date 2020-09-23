package com.stone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 日志记录，application.yml配置类
 * @date 2020/9/18 15:47
 */
@Configuration
@ConfigurationProperties(prefix = "aspect.log")
public class AspectLogProperties {

    private boolean enable;

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
