package com.example.admin.service;

import com.example.mall.dao.*;
import com.example.mall.domain.*;
import com.example.mall.domain.vo.UserOrderDetailsVO;
import com.example.mall.domain.vo.OrderDetailsVO;
import com.example.common.utils.JsonModel;
import com.example.mall.domain.form.OrderForm;
import com.example.mall.domain.vo.OrderVO;
import com.example.mall.utils.Base64Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AdminOrderService {

    @Autowired
    private UserOrderDOMapper userOrderDOMapper;

    @Autowired
    private UserOrderDetailsDOMapper userOrderDetailsDOMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRecordMapper accountRecordMapper;

    /**
     * 查询订单列表
     *
     * @param page
     * @param number
     * @param orderForm
     * @return
     */
    public JsonModel getOrderList(Integer page, Integer number, OrderForm orderForm) {
        //开启分页
        PageHelper.startPage(page, number);
        Page<OrderVO> voPage = (Page<OrderVO>) userOrderDOMapper.getOrderList(orderForm);
        PageInfo<OrderVO> pageInfo = voPage.toPageInfo();
        //返回数据
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCount(pageInfo.getTotal());
        jsonModel.setData(pageInfo.getList());
        return jsonModel;
    }

    /**
     * 更新订单状态
     *
     * @param orderForm
     */
    public synchronized void updateOrderStaus(OrderForm orderForm) {
        UserOrderDO userOrderDO = userOrderDOMapper.selectByPrimaryKey(orderForm.getId());
        userOrderDO.setStatus(orderForm.getStatus());
        userOrderDOMapper.updateByPrimaryKeySelective(userOrderDO);
        orderForm.setUserId(userOrderDO.getUserId());
        // 取消订单则需要增加退款记录及销量
        if (Objects.equals(orderForm.getStatus(), 0)) {
            Integer id = userOrderDO.getId();
            UserOrderDetailsDO query = new UserOrderDetailsDO();
            query.setOrderId(id);
            List<UserOrderDetailsDO> userOrderDetailsDOList = userOrderDetailsDOMapper.select(query);
            userOrderDetailsDOList.stream().forEach(userOrderDetailsDO -> {
                Integer productId = userOrderDetailsDO.getProductId();
                Integer num = userOrderDetailsDO.getNum();
                // 商品库存增加
                ProductDO productDO = productMapper.selectByPrimaryKey(productId);
                if (null != productDO) {
                    productDO.setNumber(productDO.getNumber() + num);
                    productDO.setSallyNum(productDO.getSallyNum() - num);
                    productMapper.updateByPrimaryKeySelective(productDO);
                }
            });

            // 退款记录
            AccountDO qu = new AccountDO();
            qu.setUserId(orderForm.getUserId());
            AccountDO accountDO = accountMapper.selectOne(qu);
            if (null != accountDO) {
                accountDO.setBalance(accountDO.getBalance().add(userOrderDO.getPrice()));
                accountMapper.updateByPrimaryKeySelective(accountDO);

                //添加退款记录
                AccountRecordDO accountRecordDO = new AccountRecordDO();
                accountRecordDO.setUserId(accountDO.getUserId());
                accountRecordDO.setUserName(accountDO.getUserName());
                accountRecordDO.setSellerName("系统");
                accountRecordDO.setCategory("1");
                accountRecordDO.setMoney(userOrderDO.getPrice());
                accountRecordDO.setCreateTime(new Date());
                accountRecordMapper.insert(accountRecordDO);
            }
        }
    }

    /**
     * 订单详情
     *
     * @param orderForm
     * @return
     */
    public JsonModel getOrderDetails(OrderForm orderForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
            OrderVO orderVO = userOrderDOMapper.getOrder(orderForm);
            orderDetailsVO.setOrderVO(orderVO);
            if (null != orderVO) {
                List<UserOrderDetailsVO> userOrderDetailsVOList = userOrderDetailsDOMapper.getOrderDetailsVOList(orderForm.getId());
                userOrderDetailsVOList.stream().forEach(userOrderDetailsVO -> {
                    String imageStr = Base64Utils.GetImageStr(userOrderDetailsVO.getImgUrl());
                    userOrderDetailsVO.setImgUrlBase64(imageStr);
                });
                orderDetailsVO.setUserOrderDetailsVOList(userOrderDetailsVOList);
            }
            jsonModel.setData(orderDetailsVO);
            jsonModel.setMsg("操作成功");
        } catch (Exception e) {
            jsonModel.setCode(500);
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }
}
