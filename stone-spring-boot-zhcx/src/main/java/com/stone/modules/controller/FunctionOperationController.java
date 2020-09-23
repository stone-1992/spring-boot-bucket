package com.stone.modules.controller;

import cn.hutool.core.io.FileUtil;
import com.stone.modules.domain.FunctionOperation;
import com.stone.modules.service.FunctionOperationService;
import com.stone.modules.service.SwaggerApiScanService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Swagger扫描功能点接口控制类
 *
 * @author Stone
 * @since 2020年9月22日 14:26:39
 */
@RestController
@RequestMapping("/func")
@Slf4j
public class FunctionOperationController {

    @Autowired
    FunctionOperationService functionOperationService;

    @Autowired
    SwaggerApiScanService swaggerApiScanService;

    /**
     * 组织功能组 List
     */
    private static final List<String> orgFunctionList = new ArrayList<>();

    /**
     * 员工功能组 List
     */
    private static final List<String> personFunctionList = new ArrayList<>();

    static {
        orgFunctionList.addAll(Arrays.asList(
                "分页搜索组织信息",
                "新增组织信息",
                "修改组织信息",
                "删除组织信息,根据组织id",
                "查询单个组织信息",
                "查询启禁用组织树",
                "查询启用组织树",
                "查询历史组织树",
                "组织调整",
                "组织禁用",
                "组织恢复启用",
                "分页查询拟调整组织",
                "分页查询拟禁用组织",
                "分页查询拟启用组织",
                "删除组织异动临时信息，取消、删除同一个接口",
                "修改组织异动临时信息",
                "查询单个组织异动临时表信息"));

        personFunctionList.addAll(Arrays.asList(
                "查询附件列表",
                "保存附件",
                "搜索员工",
                "查看员工详情",
                "导出员工",
                "新增员工",
                "下载导入员工模板",
                "导入员工",
                "查看待转正员工",
                "转正",
                "查看拟再入职员工",
                "查看拟调动员工",
                "查看拟离职员工",
                "再入职",
                "修改再入职",
                "调动",
                "修改调动",
                "离职",
                "修改离职",
                "删除员工",
                "查看员工",
                "编辑员工基础信息",
                "更新证件照",
                "取消操作",
                "删除操作",
                "查询员工异动记录",
                "查看奖惩记录列表",
                "添加奖惩记录",
                "修改奖惩记录",
                "删除奖惩记录",
                "根据员工id查询驾驶员信息",
                "新增驾驶员信息",
                "编辑驾驶员信息",
                "查询教育经历列表",
                "添加教育经历",
                "编辑教育经历",
                "删除教育经历",
                "查询工作经历列表",
                "添加工作经历",
                "修改工作经历",
                "删除工作经历"));
    }

    @ApiOperation(value = "查询该应用下的所有功能点")
    @GetMapping
    public Object getTest(@RequestParam(value = "appId") Long appId) {
        List<FunctionOperation> functionOperations = functionOperationService.listByAppId(appId);
        return functionOperations;
    }

    @ApiOperation(value = "扫描指定服务的Swagger接口文档")
    @PostMapping
    public String doScanApi() {
        try {
            swaggerApiScanService.doScanApi("http://172.16.102.169:9020/", "personnel", 103L);
            return "true";
        } catch (Exception e) {
            log.info("error message : {} ", e.getMessage());
        }
        return "false";
    }

    @ApiOperation(value = "生成应用下的功能组与功能点的关联关系")
    @GetMapping(value = "function_group")
    public String function(@RequestParam(value = "appId") Long appId) {
        List<FunctionOperation> functionOperations = functionOperationService.listByAppId(appId);
        List<FunctionOperation> orgFunctions = functionOperations.stream().filter(f -> orgFunctionList.contains(f.getName())).collect(Collectors.toList());
        List<FunctionOperation> personFunctions = functionOperations.stream().filter(f -> personFunctionList.contains(f.getName())).collect(Collectors.toList());

        String orgSql = "insert into bp_function_group_relation (function_group_id, function_operation_id ) values ( ";
        StringBuffer sql = new StringBuffer();
        orgFunctions.forEach(r -> {
            sql.append(orgSql);
            sql.append("'").append(4130).append("',");
            sql.append("'").append(r.getId()).append("');\n");
        });
        personFunctions.forEach(r -> {
            sql.append(orgSql);
            sql.append("'").append(4131).append("',");
            sql.append("'").append(r.getId()).append("');\n");
        });
        File file1 = FileUtil.newFile("D:/1/func_ope_" + appId + ".sql");
        FileUtil.appendString(String.valueOf(sql), file1, "utf-8");
        return "true";
    }
}
