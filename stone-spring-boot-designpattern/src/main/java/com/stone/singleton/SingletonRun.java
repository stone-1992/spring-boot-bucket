package com.stone.singleton;

import com.stone.singleton.ins.SingletonEHan;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/23 10:44
 */
public class SingletonRun {
    public static void main(String[] args) {
        SingletonEHan instance = SingletonEHan.getInstance();
    }
}
