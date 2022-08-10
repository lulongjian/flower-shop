import request from '../network/request';

// 查询交易记录列表
export const productListRequest = params => {
    return request({
        url: '/api/product/productList',
        method: 'post',
        params: params
    });
};

// 登录
export const lgoinRequest = params => {
    return request({
        url: '/api/sys/login',
        method: 'post',
        params: params
    });
};

// 获取验证码
export const getCodeUpdatePasswordRequest = params => {
    return request({
        url: '/api/sys/getCodeUpdatePassword',
        method: 'post',
        params: params
    });
};

// 更新密码
export const updatePasswordPasswordRequest = params => {
    return request({
        url: '/api/user/updatePassword',
        method: 'post',
        params: params
    });
};

// 留言列表
export const messageListRequest = params => {
    return request({
        url: '/api/message/messageList',
        method: 'post',
        params: params
    });
};

// 查询个人信息
export const getLoginUserRequest = params => {
    return request({
        url: '/api/user/findUser',
        method: 'post',
        params: params
    });
};

// 更新个人信息
export const updateUserRequest = params => {
    return request({
        url: '/api/user/updateUser',
        method: 'post',
        params: params
    });
};

// 查询我的地址
export const getAddressListRequest = params => {
    return request({
        url: '/api/address/list',
        method: 'post',
        params: params
    });
};

// 新增或更新地址
export const addOrUpdateAddressByIdRequest = params => {
    return request({
        url: '/api/address/addOrUpdateAddressById',
        method: 'post',
        params: params
    });
};

// 删除地址
export const deleteByAddressIdRequest = params => {
    return request({
        url: '/api/address/deleteByAddressId',
        method: 'post',
        params: params
    });
};

// 查询公告
export const getUserNoticeListRequest = params => {
    return request({
        url: '/api/notice/getUserNoticeList',
        method: 'post',
        params: params
    });
};

// 已读
export const readUserNoticeRequest = params => {
  return request({
      url: '/api/notice/readUserNotice',
      method: 'post',
      params: params
  });
};

// 获取留言信息
export const getMessageListRequest = params => {
  return request({
      url: '/api/message/messageList',
      method: 'post',
      params: params
  });
};

// 留言
export const addMessageRequest = params => {
  return request({
      url: '/api/message/addMessage',
      method: 'post',
      params: params
  });
};

// 加购物车
export const addCarRequest = params => {
  return request({
      url: '/api/car/addCar',
      method: 'post',
      params: params
  });
};

// 购物车列表
export const getCarListRequest = params => {
  return request({
      url: '/api/car/carList',
      method: 'post',
      params: params
  });
};

// 更新购物车
export const updateCarRequest = params => {
  return request({
      url: '/api/car/updateCar',
      method: 'post',
      params: params
  });
};

// 删除购物车
export const deleteCarRequest = params => {
  return request({
      url: '/api/car/deleteCar',
      method: 'post',
      params: params
  });
};

// 查询交易记录列表
export const getAccountRecordListRequest = params => {
  return request({
      url: '/api/account/getAccountRecord',
      method: 'post',
      params: params
  });
};

// 查询账户余额
export const getMyAccountListRequest = params => {
  return request({
      url: '/api/account/myAccount',
      method: 'post',
      params: params
  });
};

// 充值/体现
export const addAccountByUserIdRequest = params => {
  return request({
      url: '/api/account/addAccountByUserId',
      method: 'post',
      params: params
  });
};

// 创建订单
export const addOrderRequest = params => {
  return request({
      url: '/api/order/addOrder',
      method: 'post',
      data: params
  });
};

// 查询订单
export const getOrderListRequest = params => {
  return request({
      url: '/api/order/getOrderList',
      method: 'post',
      params: params
  });
};

// 更新订单状态
export const updateOrderStausRequest = params => {
  return request({
      url: '/api/order/updateOrderStaus',
      method: 'post',
      params: params
  });
};

// 查询订单详情
export const getOrderDetailsRequest = params => {
  return request({
      url: '/api/order/getOrderDetails',
      method: 'post',
      params: params
  });
};

// 获取购物车总价
export const getSelectPriceRequest = params => {
  return request({
      url: '/api/car/getSelectPrice',
      method: 'post',
      data: params
  });
};

// 获取商品全部类别
export const getProductTypeAllListRequest = params => {
  return request({
      url: '/api/product/type/getProductTypeAllList',
      method: 'post',
      params: params
  });
};

// 查询每日推荐列表
export const getRecommendListRequest = params => {
  return request({
      url: '/api/recommend/getRecommendList',
      method: 'post',
      params: params
  });
};

// 查询每日花语
export const getFlowerLanguageRequest = params => {
  return request({
      url: '/api/flower/language/getFlowerLanguage',
      method: 'post',
      params: params
  });
};


// 查询养花知识
export const getFlowerKnowledgeRequest = params => {
  return request({
      url: '/api/flower/knowledge/getFlowerKnowledge',
      method: 'post',
      params: params
  });
};
