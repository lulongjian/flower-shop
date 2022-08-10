import request from '../utils/request';

// 查询养花知识记录列表
export const getFlowerKnowledgeListRequest = params => {
    return request({
        url: '/api/admin/flower/knowledge/getFlowerKnowledgeList',
        method: 'post',
        params: params
    });
};

// 新增养花知识
export const addFlowerKnowledgeRequestRequest = params => {
    return request({
        url: '/api/admin/flower/knowledge/addFlowerKnowledge',
        method: 'post',
        params: params
    });
};

// 更新养花知识
export const updateFlowerKnowledgeRequestRequest = params => {
    return request({
        url: '/api/admin/flower/knowledge/updateFlowerKnowledge',
        method: 'post',
        params: params
    });
};

// 删除养花知识
export const delFlowerKnowledgeRequestRequest = params => {
    return request({
        url: '/api/admin/flower/knowledge/deleteFlowerKnowledge/' + params,
        method: 'get'
    });
};

