package com.example.mall.controller;

import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.OrderForm;
import com.example.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单并付款
     */
    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    public JsonModel addOrder(@RequestBody OrderForm orderForm) {
        return orderService.addOrder(orderForm);
    }

    /**
     * 查询订单列表
     *
     * @return
     */
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST)
    public JsonModel getOrderList(Integer page, Integer number, OrderForm orderForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            jsonModel = orderService.getOrderList(page, number, orderForm);
            jsonModel.setMsg("加载成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

    /**
     * 更新订单状态
     *
     * @return
     */
    @RequestMapping(value = "updateOrderStaus", method = RequestMethod.POST)
    public JsonModel updateOrderStaus(OrderForm orderForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            orderService.updateOrderStaus(orderForm);
            jsonModel.setMsg("更新成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }


    /**
     * 订单详情
     *
     * @param orderForm
     * @return
     */
    @RequestMapping(value = "getOrderDetails", method = RequestMethod.POST)
    public JsonModel getOrderDetails(OrderForm orderForm) {
        return orderService.getOrderDetails(orderForm);
    }
}
