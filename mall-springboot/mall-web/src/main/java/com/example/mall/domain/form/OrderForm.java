package com.example.mall.domain.form;

import lombok.Data;

import java.util.List;

@Data
public class OrderForm {

	private Integer id; // id

	private Integer userId;// 用户id

	private Integer orderNo;// 订单编号

	private Integer status;// 订单状态

	private List<ProductForm> productFormList; // 商品列表

	private AddressForm addressForm; // 地址信息
}
