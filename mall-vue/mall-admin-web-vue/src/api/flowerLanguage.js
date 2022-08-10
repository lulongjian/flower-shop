import request from '../utils/request';

// 查询每日花语列表
export const getFlowerLanguageListRequest = params => {
    return request({
        url: '/api/admin/flower/language/getFlowerLanguageList',
        method: 'post',
        params: params
    });
};

// 新增花语
export const addFlowerLanguageRequest = params => {
    return request({
        url: '/api/admin/flower/language/addFlowerLanguage',
        method: 'post',
        params: params
    });
};

// 更新花语
export const updateFlowerLanguageRequest = params => {
    return request({
        url: '/api/admin/flower/language/updateFlowerLanguage',
        method: 'post',
        params: params
    });
};

// 删除花语
export const deleteFlowerLanguageRequest = params => {
    return request({
        url: '/api/admin/flower/language/deleteFlowerLanguage/' + params,
        method: 'get'
    });
};
