package com.stone.service;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: ExampleService
 * @date 2020/9/18 14:58
 */
public class ExampleService {

    private String prefix;

    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
