import request from '../utils/request';

// 查询商品列表
export const getProductListRequest = params => {
    return request({
        url: '/api/admin/product/getProduct',
        method: 'post',
        params: params
    });
};


// 新增商品
export const addProductRequest = params => {
    return request({
        url: '/api/admin/product/addProduct',
        method: 'post',
        params: params
    });
};

// 更新商品
export const updateProductRequest = params => {
    return request({
        url: '/api/admin/product/updateProduct',
        method: 'post',
        params: params
    });
};

// 上架商品
export const upProductRequest = params => {
    return request({
        url: '/api/admin/product/upProduct/' + params,
        method: 'get'
    });
};

// 下架商品
export const deProductRequest = params => {
    return request({
        url: '/api/admin/product/deProduct/' + params,
        method: 'get'
    });
};


// 查询商品，下拉选择
export const selectProductListRequest = params => {
    return request({
        url: '/api/admin/product/selectProductList',
        method: 'get'
    });
};
