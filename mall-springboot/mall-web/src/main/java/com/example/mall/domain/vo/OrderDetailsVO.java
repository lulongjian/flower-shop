package com.example.mall.domain.vo;

import com.example.mall.domain.UserOrderDO;
import com.example.mall.domain.UserOrderDetailsDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class OrderDetailsVO {

	private OrderVO orderVO;

	private List<UserOrderDetailsVO> userOrderDetailsVOList;
}
