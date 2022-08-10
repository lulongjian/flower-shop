package com.example.mall.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVO {

    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 订单状态, 1 已付款/待发货 3 已完成 2 已发货 0 已取消
     */
    private Integer status;

    /**
     * 订单总价
     */
    private BigDecimal price;

    /**
     * 地址详细
     */
    private String addrDes;

    /**
     * 地址收货人
     */
    private String addrName;

    /**
     * 地址电话
     */
    private String addrPhone;

    /**
     * 付款记录id
     */
    private Integer accountRecordId;

    /**
     * 生成日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
