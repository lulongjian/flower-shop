package com.example.mall.service;
import java.util.Date;

import com.example.common.utils.JsonModel;
import com.example.mall.dao.*;
import com.example.mall.domain.*;
import com.example.mall.domain.form.OrderForm;
import com.example.mall.domain.form.ProductForm;
import com.example.mall.domain.vo.OrderDetailsVO;
import com.example.mall.domain.vo.OrderVO;
import com.example.mall.domain.vo.UserOrderDetailsVO;
import com.example.mall.utils.Base64Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRecordMapper accountRecordMapper;

    @Autowired
    private UserOrderDOMapper userOrderDOMapper;

    @Autowired
    private UserOrderDetailsDOMapper userOrderDetailsDOMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * 创建订单并付款
     *
     * @param orderForm
     * @return
     */
    public JsonModel addOrder(OrderForm orderForm) {
        JsonModel jsonModel = new JsonModel();
        try {
            BigDecimal allMoney = new BigDecimal(0);
            // 判断商品库存信息
            List<ProductForm> productFormList = orderForm.getProductFormList();
            for (ProductForm productForm : productFormList) {
                ProductDO productDO = productMapper.selectByPrimaryKey(productForm.getProductId());
                if (Objects.equals(productDO.getPermission(), "0")) {
                    jsonModel.setCode(1);
                    jsonModel.setMsg("创建订单失败，订单存在已下架商品！请刷新重试");
                    return jsonModel;
                }
                if (productDO.getNumber() <productForm.getNumber() ) {
                    jsonModel.setCode(1);
                    jsonModel.setMsg("创建订单失败，订单存在商品库存不足！请刷新重试");
                    return jsonModel;
                }
                BigDecimal multiply = productDO.getPrice().multiply(new BigDecimal(productForm.getNumber()));
                allMoney = allMoney.add(multiply);
            }

            // 判断账户是否足够金额下单
            AccountDO query = new AccountDO();
            query.setUserId(orderForm.getUserId());
            AccountDO accountDO = accountService.findAccountByUserId(query);
            if (accountDO.getBalance().compareTo(allMoney) == -1) {
                jsonModel.setCode(1);
                jsonModel.setMsg("创建订单失败，余额不足！请刷新重试");
                return jsonModel;
            }

            UserDO userDO = userMapper.selectByPrimaryKey(orderForm.getUserId());

            // 付款
            accountDO.setBalance(accountDO.getBalance().subtract(allMoney));
            accountMapper.updateByPrimaryKeySelective(accountDO);

            // 付款记录
            AccountRecordDO accountRecordDO = new AccountRecordDO();
            accountRecordDO.setUserId(orderForm.getUserId());
            accountRecordDO.setUserName(userDO.getName());
            accountRecordDO.setSellerName("系统");
            accountRecordDO.setCategory("0");
            accountRecordDO.setMoney(allMoney);
            accountRecordDO.setCreateTime(new Date());
            accountRecordMapper.insertSelective(accountRecordDO);

            // 创建订单
            UserOrderDO userOrderDO = new UserOrderDO();
            userOrderDO.setOrderNo("T" + System.currentTimeMillis() + userDO.getId());
            userOrderDO.setUserId(orderForm.getUserId());
            userOrderDO.setStatus(1);
            userOrderDO.setPrice(allMoney);
            userOrderDO.setAddrDes(orderForm.getAddressForm().getAddr());
            userOrderDO.setAddrName(orderForm.getAddressForm().getUserName());
            userOrderDO.setAddrPhone(orderForm.getAddressForm().getUserPhone());
            userOrderDO.setAccountRecordId(accountRecordDO.getId());
            userOrderDO.setCreateTime(new Date());
            userOrderDO.setUpdateTime(new Date());
            userOrderDOMapper.insertSelective(userOrderDO);

            // 创建订单详情
            for (ProductForm productForm : productFormList) {
                ProductDO productDO = productMapper.selectByPrimaryKey(productForm.getProductId());
                UserOrderDetailsDO userOrderDetailsDO = new UserOrderDetailsDO();
                userOrderDetailsDO.setOrderId(userOrderDO.getId());
                userOrderDetailsDO.setProductId(productDO.getId());
                userOrderDetailsDO.setProductPrice(productDO.getPrice());
                userOrderDetailsDO.setNum(productForm.getNumber());
                userOrderDetailsDO.setPrice(productDO.getPrice().multiply(new BigDecimal(productForm.getNumber())));
                userOrderDetailsDOMapper.insertSelective(userOrderDetailsDO);

                // 商品销量增加，库存减少
                productDO.setSallyNum(productDO.getSallyNum() + productForm.getNumber());
                productDO.setNumber(productDO.getNumber() - productForm.getNumber());
                productMapper.updateByPrimaryKeySelective(productDO);

                // 删除购物车
                CarDO carDO = new CarDO();
                carDO.setUserId(userDO.getId());
                carDO.setProductId(productDO.getId());
                carMapper.delete(carDO);
            }

        }catch (Exception e){
            jsonModel.setMsg("网络错误！请刷新重试");
        }
        return jsonModel;
    }

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
