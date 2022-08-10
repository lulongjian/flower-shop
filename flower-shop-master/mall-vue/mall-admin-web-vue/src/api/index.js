import request from '../utils/request';

export const fetchData = params => {
    return request({
        url: './table.json',
        method: 'get',
        params: params
    });
};
