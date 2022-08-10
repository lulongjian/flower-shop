import request from '../utils/request';

// 查询用户列表
export const getUserListRequest = params => {
    return request({
        url: '/api/admin/user/getUser',
        method: 'post',
        params: params
    });
};

// 新增用户
export const addUserRequest = params => {
    return request({
        url: '/api/admin/user/addUser',
        method: 'post',
        params: params
    });
};

// 编辑用户
export const updateUserRequest = params => {
    return request({
        url: '/api/admin/user/updateUser',
        method: 'post',
        params: params
    });
};

// 冻结用户
export const deleteUserRequest = params => {
    return request({
        url: '/api/admin/user/deleteUser',
        method: 'post',
        params: params
    });
};

// 重置密码
export const passwordReset = params => {
    return request({
        url: '/api/admin/user/passwordReset/' + params,
        method: 'get'
    });
};

// 查找用户列表（下拉选择）
export const selectUserListRequest = params => {
    return request({
        url: '/api/admin/user/selectUserList',
        method: 'get'
    });
};
