import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '首页' },
            children: [
                {
                    path: '/Index',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Index.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/UserManage',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/UserManage.vue'),
                    meta: { title: '会员管理' }
                },
                {
                    path: '/ProductTypeManage',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/ProductTypeManage.vue'),
                    meta: { title: '类别管理' }
                },
                {
                  path: '/ProductManage',
                  component: () => import(/* webpackChunkName: "icon" */ '../components/page/ProductManage.vue'),
                  meta: { title: '鲜花管理' }
                },
                {
                    path: '/RecommendManage',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/RecommendManage.vue'),
                    meta: { title: '每日推荐' }
                },
                {
                  path: '/FlowerLanguageManage',
                  component: () => import(/* webpackChunkName: "icon" */ '../components/page/FlowerLanguageManage.vue'),
                  meta: { title: '每日花语' }
                },
                {
                  path: '/FlowerKnowledgeManage',
                  component: () => import(/* webpackChunkName: "icon" */ '../components/page/FlowerKnowledgeManage.vue'),
                  meta: { title: '养花知识' }
                },
                {
                    path: '/OrderManage',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/OrderManage.vue'),
                    meta: { title: '订单管理' }
                },
                {
                    path: '/AccountRecordManage',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/AccountRecordManage.vue'),
                    meta: { title: '交易记录' }
                },
                {
                  path: '/MessageManage',
                  component: () => import(/* webpackChunkName: "icon" */ '../components/page/MessageManage.vue'),
                  meta: { title: '留言管理' }
              },
                {
                    path: '/NoticeManage',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/NoticeManage.vue'),
                    meta: { title: '公告管理' }
                }
            ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
