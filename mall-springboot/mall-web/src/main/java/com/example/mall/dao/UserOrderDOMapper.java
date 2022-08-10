package com.example.mall.dao;

import com.example.common.thMapper.TkMapper;
import com.example.mall.domain.UserOrderDO;
import com.example.mall.domain.form.OrderForm;
import com.example.mall.domain.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrderDOMapper extends TkMapper<UserOrderDO> {

    /**
     * 查询订单列表
     *
     * @param orderForm
     * @return
     */
    List<OrderVO> getOrderList(OrderForm orderForm);

    /**
     * 订单详情
     *
     * @param orderForm
     * @return
     */
    OrderVO getOrder(OrderForm orderForm);

    /**
     * 获取订单数量
     *
     * @param date
     * @return
     */
    int getOrderNumByCrtDate(String date);
}