package com.example.mall.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "recommend")
public class RecommendDO {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "product_id")
	private Integer productId; // 商品id

	@Column(name = "sort")
	private Integer sort; // 排序

	@Column(name = "re_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date reTime; //推荐日期

	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime; //添加时间
}
