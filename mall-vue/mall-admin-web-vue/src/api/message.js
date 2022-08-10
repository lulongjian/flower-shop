import request from '../utils/request';

// 查询留言列表
export const getMessageListRequest = params => {
    return request({
        url: '/api/admin/message/getMessageList',
        method: 'post',
        params: params
    });
};

// 删除留言
export const deleteMessageRequest = params => {
    return request({
        url: '/api/admin/message/deleteMessage/' + params,
        method: 'get'
    });
};
