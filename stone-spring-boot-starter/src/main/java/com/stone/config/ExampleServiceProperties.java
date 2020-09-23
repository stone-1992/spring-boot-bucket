package com.stone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 测试 spring-boot-starter
 * @date 2020/9/18 14:57
 */
@ConfigurationProperties("example.service")
public class ExampleServiceProperties {

    private String prefix;

    private String suffix;

    private boolean enable;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean getEnable() {
        return enable;
    }
}