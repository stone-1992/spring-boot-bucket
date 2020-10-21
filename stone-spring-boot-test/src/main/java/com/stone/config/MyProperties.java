package com.stone.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Data
@Configuration
@ConfigurationProperties(prefix = "my.properties")
public class MyProperties {

    private List<String> list;

    private boolean enabled = false;

    private String logInfo;
}
