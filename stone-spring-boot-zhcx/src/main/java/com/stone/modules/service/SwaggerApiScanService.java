package com.stone.modules.service;

import com.stone.modules.domain.FunctionOperation;

import java.util.List;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/21 18:29
 */
public interface SwaggerApiScanService {

    /**
     * 扫描api接口
     *
     * @param project 项目名
     * @param appId   应用id(非必传)
     * @return
     * @throws Exception
     */
    List<FunctionOperation> doScanApi(String project, Long appId) throws Exception;

    /**
     * 扫描api接口
     *
     * @param projectUrlPrefix 项目路径前缀
     * @param project          项目名
     * @param appId            应用id
     * @return
     * @throws Exception
     */
    List<FunctionOperation> doScanApi(String projectUrlPrefix, String project, Long appId) throws Exception;
}
