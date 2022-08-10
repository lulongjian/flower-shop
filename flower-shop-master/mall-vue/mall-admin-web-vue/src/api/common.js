import request from '../utils/request';

// 登录
export const loginRequest = params => {
    return request({
        url: '/api/admin/sys/login',
        method: 'post',
        params: params
    });
};

// 首页

export const getSystemData = params => {
    return request({
        url: '/api/admin/sys/getSystemData',
        method: 'post',
        params: params
    });
};