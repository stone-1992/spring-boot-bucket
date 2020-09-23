package com.stone.modules.service.impl;

import com.stone.modules.domain.FunctionOperation;
import com.stone.modules.service.FunctionOperationService;
import com.stone.modules.service.SwaggerApiScanService;
import com.stone.modules.service.crawler.SwaggerApiCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: Swagger 接口扫描实现类
 * @date 2020/9/21 18:29
 */
@Service
public class SwaggerApiScanServiceImpl implements SwaggerApiScanService {

    @Autowired
    private FunctionOperationService functionOperationService;

    @Override
    public List<FunctionOperation> doScanApi(String project, Long appId) throws Exception {
        // 爬取swagger api接口并保存到功能表中
        SwaggerApiCrawler crawler = new SwaggerApiCrawler(project, true);
        crawler.setFunctionOperationService(functionOperationService);
        crawler.setAppId(appId);
        crawler.start(1);
        return null;
    }

    @Override
    public List<FunctionOperation> doScanApi(String projectUrlPrefix, String project, Long appId) throws Exception {
        // 爬取swagger api接口并保存到功能表中
        SwaggerApiCrawler crawler = new SwaggerApiCrawler(projectUrlPrefix, project, true);
        crawler.setFunctionOperationService(functionOperationService);
        crawler.setAppId(appId);
        crawler.start(1);
        return null;
    }
}
