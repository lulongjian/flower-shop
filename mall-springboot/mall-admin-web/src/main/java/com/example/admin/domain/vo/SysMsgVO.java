package com.example.admin.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class SysMsgVO {
	private Integer authUserNum;//已认证用户数
	private Integer authProductNum;//已授权商品数
	private Integer userOrderNum;//全部订单
	private Integer recommendNum;//每日推荐
	private Integer orderNum1;//待发货
	private Integer orderNum2;//已发货
	private Integer orderNum3;//已完成
	private Integer orderNum0;//已取消
	private List<EchartsVO> orderList;//订单柱状图
}
