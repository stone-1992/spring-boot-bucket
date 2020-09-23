package com.stone.modules.service;

import com.stone.modules.domain.FunctionOperation;

import java.util.List;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 功能点接口超类
 * @date 2020/9/21 18:10
 */
public interface FunctionOperationService {

    /**
     * 获取应用下所有的功能点
     *
     * @param appId
     * @return
     */
    List<FunctionOperation> listByAppId(Long appId);


    /**
     * 判断改应用下功能点是否存在
     *
     * @param functionOperation
     * @return
     */
    boolean funcIsExist(FunctionOperation functionOperation);
}
