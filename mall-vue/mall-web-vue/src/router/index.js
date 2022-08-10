import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const AllProduct = () => import('../views/product/AllProduct')
const Home = () => import('../views/home/Home')
const Help = () => import('../views/help/Help')
const Login = () => import('../views/login/Login')
const Reg = () => import('../views/reg/Reg')
const Car = () => import('../components/car/Car')
const Order = () => import('../views/order/order')
const personCenter = () => import('../views/personCenter/personCenter')
const forgetPassword = () => import('../views/forgetPassword/forgetPassword')
const messageCenter = () => import('../views/messageCenter/messageCenter')
const Recommend = () => import('../views/product/Recommend')
const NewProduct = () => import('../views/product/NewProduct')
const HotProduct = () => import('../views/product/HotProduct')
const flowerKnowledge = () => import('../views/flowerKnowledge/flowerKnowledge')

const routes = [
	{
		path: '', 
		redirect: '/home' 
	},
  {
		path: '/home',
		component: Home,
		meta: {
			title: '首页' 
		},
	},
	{
		path: '/product',
		component: AllProduct,
		meta: {
			title: '全部商品' 
		},
	},
  {
		path: '/recommend',
		component: Recommend,
		meta: {
			title: '每日推荐' 
		},
	},
  {
		path: '/newProduct',
		component: NewProduct,
		meta: {
			title: '新品上市' 
		},
	},
  {
		path: '/hotProduct',
		component: HotProduct,
		meta: {
			title: '热门销量' 
		},
	},
  {
		path: '/flowerKnowledge',
		component: flowerKnowledge,
		meta: {
			title: '养花小知识' 
		},
	},
	{
		path: '/help',
		component: Help,
		meta: { 
			title: '帮助' 
		}
	},
	{
		path: '/login',
		component: Login,
		meta: { 
			title: '登陆' 
		}
	},
	{
		path: '/reg',
		component: Reg,
		meta: {
			title: '注册' 
		}
	},
	{
		path: '/car',
		component: Car,
		meta: {
			title: '购物车'
		}
	},
	{
		path: '/order',
		component: Order,
		meta: {
			title: '订单' 
		}
	},
	{
		path: '/personCenter',
		component: personCenter,
		meta: {
			title: '个人中心' 
		}
	},
	{
		path: '/forgetPassword',
		component: forgetPassword,
		meta: {
			title: '忘记密码' 
		}
	},
	{
		path: '/messageCenter',
		component: messageCenter,
		meta: {
			title: '消息中心' 
		}
	}
]

const router = new Router({
	routes,
	mode: 'hash',
	linkActiveClass: 'nav_active'
})

router.beforeEach((to, from, next) => {
	document.title = `时茪集 | ` + to.matched[0].meta.title;
	next()
})

export default router
