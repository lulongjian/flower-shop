package com.example.mall.domain.vo;

import com.example.mall.domain.ProductDO;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @Author: 12198
 * @Time: 2022/3/28 17:03
 * @Description:
 */
@Data
public class UserOrderDetailsVO extends ProductVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 购买价格
     */
    private BigDecimal productPrice;

    /**
     * 购买总数量
     */
    private Integer num;

    /**
     * 购买总价格
     */
    private BigDecimal price;


}
