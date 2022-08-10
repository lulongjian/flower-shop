import axios from 'axios';
import { Message } from 'element-ui';

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

const service = axios.create({
  timeout: 5000
});

service.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    console.log(error);
    return Promise.reject();
  }
);

service.interceptors.response.use(
    response => {
        if (response.status === 200) {
            const code = response.data.code
            if (code !== 0) {
                Message.error(response.data.msg)
                return Promise.reject(new Error(response.data.msg || "请求失败"));
            } else{
                return response.data;
            }
        } else {
            Message.error("请求失败")
            return Promise.reject(new Error("请求失败"));
        }
    },
    error => {
        Message.error("请求失败")
        return Promise.reject(new Error("请求失败"));
    }
);

export default service;
