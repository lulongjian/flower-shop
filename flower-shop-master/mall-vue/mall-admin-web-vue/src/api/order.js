import request from '../utils/request';

// 查询订单列表
export const getOrderListRequest = params => {
    return request({
        url: '/api/admin/order/getOrderList',
        method: 'post',
        params: params
    });
};


// 更新状态
export const updateOrderStausRequest = params => {
    return request({
        url: '/api/admin/order/updateOrderStaus',
        method: 'post',
        params: params
    });
};

// 订单详情
export const getOrderDetailsRequest = params => {
    return request({
        url: '/api/admin/order/getOrderDetails',
        method: 'post',
        params: params
    });
};

