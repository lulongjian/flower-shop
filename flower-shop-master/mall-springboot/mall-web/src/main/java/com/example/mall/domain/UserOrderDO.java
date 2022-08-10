package com.example.mall.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_order")
public class UserOrderDO {
    /**
     * id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

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
    @Column(name = "addr_des")
    private String addrDes;

    /**
     * 地址收货人
     */
    @Column(name = "addr_name")
    private String addrName;

    /**
     * 地址电话
     */
    @Column(name = "addr_phone")
    private String addrPhone;

    /**
     * 付款记录id
     */
    @Column(name = "account_record_id")
    private Integer accountRecordId;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取订单状态, 1 已付款/待发货 3 已完成 2 已发货 0 已取消
     *
     * @return status - 订单状态, 1 已付款/待发货 3 已完成 2 已发货 0 已取消
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态, 1 已付款/待发货 3 已完成 2 已发货 0 已取消
     *
     * @param status 订单状态, 1 已付款/待发货 3 已完成 2 已发货 0 已取消
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取订单总价
     *
     * @return price - 订单总价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置订单总价
     *
     * @param price 订单总价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取地址详细
     *
     * @return addr_des - 地址详细
     */
    public String getAddrDes() {
        return addrDes;
    }

    /**
     * 设置地址详细
     *
     * @param addrDes 地址详细
     */
    public void setAddrDes(String addrDes) {
        this.addrDes = addrDes == null ? null : addrDes.trim();
    }

    /**
     * 获取地址收货人
     *
     * @return addr_name - 地址收货人
     */
    public String getAddrName() {
        return addrName;
    }

    /**
     * 设置地址收货人
     *
     * @param addrName 地址收货人
     */
    public void setAddrName(String addrName) {
        this.addrName = addrName == null ? null : addrName.trim();
    }

    /**
     * 获取地址电话
     *
     * @return addr_phone - 地址电话
     */
    public String getAddrPhone() {
        return addrPhone;
    }

    /**
     * 设置地址电话
     *
     * @param addrPhone 地址电话
     */
    public void setAddrPhone(String addrPhone) {
        this.addrPhone = addrPhone == null ? null : addrPhone.trim();
    }

    /**
     * 获取付款记录id
     *
     * @return account_record_id - 付款记录id
     */
    public Integer getAccountRecordId() {
        return accountRecordId;
    }

    /**
     * 设置付款记录id
     *
     * @param accountRecordId 付款记录id
     */
    public void setAccountRecordId(Integer accountRecordId) {
        this.accountRecordId = accountRecordId;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}