import request from '../utils/request';

// 查询商品类别列表
export const getProductTypeListRequest = params => {
    return request({
        url: '/api/admin/produce/type/getProductTypeList',
        method: 'post',
        params: params
    });
};

// 新增商品类别
export const addProduceTypeRequest = params => {
    return request({
        url: '/api/admin/produce/type/addProduceType',
        method: 'post',
        params: params
    });
};

// 更新商品类别
export const updateProductTypeRequest = params => {
    return request({
        url: '/api/admin/produce/type/updateProductType',
        method: 'post',
        params: params
    });
};

// 删除商品类别
export const deleteProductTypeRequest = params => {
    return request({
        url: '/api/admin/produce/type/deleteProductType',
        method: 'post',
        params: params
    });
};

// 获取商品全部类别
export const getProductTypeAllListRequest = params => {
  return request({
      url: '/api/admin/produce/type/getProductTypeAllList',
      method: 'post',
      params: params
  });
};
