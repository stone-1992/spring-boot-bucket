package com.stone.modules.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.modules.domain.FunctionOperation;
import com.stone.modules.mapper.FunctionOperationMapper;
import com.stone.modules.service.FunctionOperationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: 功能点 Service 实现类
 * @date 2020/9/21 18:11
 */
@Service
public class FunctionOperationServiceImpl extends ServiceImpl<FunctionOperationMapper, FunctionOperation> implements FunctionOperationService {

    @Override
    public List<FunctionOperation> listByAppId(Long appId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("app_id", appId);
        return super.list(queryWrapper);
    }

    @Override
    public boolean funcIsExist(FunctionOperation functionOperation) {
        QueryWrapper<FunctionOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id", functionOperation.getAppId());
        queryWrapper.eq("request_type", functionOperation.getRequestType());
        queryWrapper.eq("url", functionOperation.getUrl());
        List<FunctionOperation> list = super.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return true;
        }
        return false;
    }
}
