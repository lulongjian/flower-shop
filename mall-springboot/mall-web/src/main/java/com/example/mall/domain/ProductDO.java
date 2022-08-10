package com.example.mall.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "product")
public class ProductDO {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "permission")
	private String permission;//商品授权

	@Column(name = "title")
	private String title;//商品标题

	@Column(name = "sort")
	private String sort;//商品类别

	@Column(name = "number")
	private Integer number;//商品数量

	@Column(name = "des")
	private String des;//商品描述

	@Column(name = "user_id")
	private Integer userId;//用户id

	@Column(name = "img_url")
	private String imgUrl;//图片地址

	@Column(name = "price")
	private BigDecimal price;//价格

	@Column(name = "old_price")
	private BigDecimal oldPrice;//原价格

	@Column(name = "collect_num")
	private Integer collectNum;//收藏数

	@Column(name = "sally_num")
	private Integer sallyNum;//销量

	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;//发布日期

	@Column(name = "update_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;//更新日期
}
