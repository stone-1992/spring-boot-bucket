package com.stone.bean;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息搜索对象
 *
 * @date 2019年6月12日
 */
@Data
public class MessageDO implements Serializable {

    private static final long serialVersionUID = -4385002751396898832L;

    /**
     * 业务id
     */
    @Field
    private String id;

    /**
     * 企业id
     */
    @Field
    private Long corpId;

    /**
     * 线路id
     */
    @Field
    private Long lineId;

    /**
     * 车辆ID
     */
    @Field
    private Long vehicleId;

    /**
     * 驾驶员员工id
     */
    @Field
    private Long driverEmplId;

    /**
     * 消息大类型 1 : 调度通知 2 : 预报信息 3 : 违规信息 4 : 报警信息 5 : 非营运信息 6 : 打卡信息
     */
    @Field
    private Integer noticeBigType;

    /**
     * 消息小类 :
     * 报警信息： 1 : 超速 2 : 低速  3 : 滞站 4 : 越站
     * 。。。
     */
    @Field
    private String noticeType;

    /**
     * 消息内容
     */
    @Field
    private String noticeMessage;

    /**
     * 创建人
     */
    @Field
    private Long creator;

    /**
     * 创建时间
     */
    @Field
    private Date creatTime;

    /**
     * 修改人
     */
    @Field
    private Long modifier;

    /**
     * 修改时间
     */
    @Field
    private Date modifyTime;

}
