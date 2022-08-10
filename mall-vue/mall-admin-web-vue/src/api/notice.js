import request from '../utils/request';

// 查询系统通知记录列表
export const getNoticeListRequest = params => {
    return request({
        url: '/api/admin/notice/getNoticeList',
        method: 'post',
        params: params
    });
};

// 新增系统通知
export const addNoticeRequestRequest = params => {
    return request({
        url: '/api/admin/notice/addNotice',
        method: 'post',
        params: params
    });
};

// 更新系统通知
export const updateNoticeRequestRequest = params => {
    return request({
        url: '/api/admin/notice/updateNotice',
        method: 'post',
        params: params
    });
};

// 删除系统通知
export const delNoticeRequestRequest = params => {
    return request({
        url: '/api/admin/notice/deleteNotice',
        method: 'post',
        params: params
    });
};

