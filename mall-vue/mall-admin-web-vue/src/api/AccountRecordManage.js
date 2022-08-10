import request from '../utils/request';

// 查询交易记录列表
export const getAccountRecordListRequest = params => {
    return request({
        url: '/api/admin/account/getAccountRecord',
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

