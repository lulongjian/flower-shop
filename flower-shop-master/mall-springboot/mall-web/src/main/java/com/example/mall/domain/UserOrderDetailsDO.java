package com.example.mall.domain;

import lombok.Data;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@Table(name = "user_order_details")
public class UserOrderDetailsDO {
    @Id
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 购买价格
     */
    @Column(name = "product_price")
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