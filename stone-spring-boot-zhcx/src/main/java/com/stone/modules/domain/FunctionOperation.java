package com.stone.modules.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 功能点扫描结果实体
 *
 * @author Stone
 * @since 2020年9月22日 14:29:42
 */
@TableName(value = "bp_function_operation")
@Data
public class FunctionOperation {

    /**
     * 主键
     */
    private Long id;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 功能名称
     */
    private String name;

    /**
     * 功能标识
     */
    private String code;

    /**
     * 请求类型 GET POST PUT DELETE
     */
    private String requestType;

    /**
     * 请求uri
     */
    private String url;

    /**
     * 排序序号
     */
    private Integer orderNum;

}
