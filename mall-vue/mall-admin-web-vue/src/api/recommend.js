import request from '../utils/request';

// 查询每日推荐列表
export const getRecommendListRequest = params => {
    return request({
        url: '/api/admin/recommend/getRecommendList',
        method: 'post',
        params: params
    });
};

// 新增推荐
export const addRecommendRequest = params => {
    return request({
        url: '/api/admin/recommend/addRecommend',
        method: 'post',
        params: params
    });
};

// 更新推荐
export const updateRecommendRequest = params => {
    return request({
        url: '/api/admin/recommend/updateRecommend',
        method: 'post',
        params: params
    });
};

// 删除推荐
export const deleteRecommendRequest = params => {
    return request({
        url: '/api/admin/recommend/deleteRecommend/' + params,
        method: 'get'
    });
};
