-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.35-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 导出  表 fleamarket.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(40) DEFAULT NULL COMMENT '用户名字',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='账户';

-- 正在导出表  fleamarket.account 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `user_id`, `user_name`, `balance`) VALUES
	(1, 1, '测试1号', 800.10),
	(2, 2, '嘎嘎是', 9967.00);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 导出  表 fleamarket.account_record 结构
CREATE TABLE IF NOT EXISTS `account_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(40) DEFAULT NULL COMMENT '用户名字',
  `seller_id` int(11) DEFAULT NULL COMMENT '收款方',
  `seller_name` varchar(40) DEFAULT NULL COMMENT '收款人',
  `category` varchar(10) DEFAULT NULL COMMENT '类别（+-金额）0支出 1收入 3充 4提现',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `money_str` varchar(255) DEFAULT NULL COMMENT '金额描述',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='交易记录';

-- 正在导出表  fleamarket.account_record 的数据：~18 rows (大约)
/*!40000 ALTER TABLE `account_record` DISABLE KEYS */;
INSERT INTO `account_record` (`id`, `user_id`, `user_name`, `seller_id`, `seller_name`, `category`, `money`, `create_time`, `money_str`, `order_id`) VALUES
	(1, 1, '测试1号', NULL, '系统', '3', 1000.00, '2022-04-05 21:13:02', NULL, NULL),
	(2, 1, '测试1号', NULL, '系统', '0', 19.99, '2022-04-05 21:14:58', NULL, NULL),
	(3, 1, '测试1号', NULL, '系统', '0', 79.96, '2022-04-06 09:05:51', NULL, NULL),
	(4, 1, '测试1号', NULL, '系统', '0', 59.97, '2022-04-06 09:13:50', NULL, NULL),
	(5, 1, '测试1号', NULL, '系统', '0', 19.99, '2022-04-06 09:17:19', NULL, NULL),
	(6, 1, '测试1号', NULL, '系统', '0', 39.98, '2022-04-10 19:59:08', NULL, NULL),
	(7, 1, '测试1号', NULL, '系统', '1', 39.98, '2022-04-10 20:18:37', NULL, NULL),
	(8, 1, '测试1号', NULL, '系统', '0', 19.99, '2022-04-10 20:19:44', NULL, NULL),
	(9, 1, '测试1号', NULL, '系统', '1', 19.99, '2022-04-10 20:19:56', NULL, NULL),
	(10, 1, '测试1号', NULL, '系统', '1', 59.97, '2022-04-10 20:29:06', NULL, NULL),
	(11, 1, '测试1号', NULL, '系统', '0', 19.99, '2022-04-10 23:15:51', NULL, NULL),
	(12, 1, '测试1号', NULL, '系统', '0', 19.99, '2022-04-10 23:17:34', NULL, NULL),
	(13, 1, '测试1号', NULL, '系统', '1', 19.99, '2022-04-10 23:17:56', NULL, NULL),
	(14, 1, '测试1号', NULL, '系统', '1', 19.99, '2022-04-17 22:25:28', NULL, NULL),
	(15, 1, '测试1号', NULL, '系统', '0', 79.96, '2022-04-26 22:04:40', NULL, NULL),
	(16, 2, '嘎嘎是', NULL, '系统', '3', 10000.00, '2022-05-03 14:23:22', NULL, NULL),
	(17, 2, '嘎嘎是', NULL, '系统', '0', 33.00, '2022-05-03 14:23:31', NULL, NULL),
	(18, 2, '嘎嘎是', NULL, '系统', '0', 179.91, '2022-05-03 15:57:53', NULL, NULL),
	(19, 2, '嘎嘎是', NULL, '系统', '1', 179.91, '2022-05-04 14:04:56', NULL, NULL);
/*!40000 ALTER TABLE `account_record` ENABLE KEYS */;

-- 导出  表 fleamarket.address 结构
CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(40) DEFAULT NULL COMMENT '收货人姓名',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '收货人手机号码',
  `addr` varchar(255) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地址';

-- 正在导出表  fleamarket.address 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`id`, `user_id`, `user_name`, `user_phone`, `addr`) VALUES
	(1, 1, '测试收货人', '13978999127', '测试地址测试地址测试地址'),
	(2, 2, '12', '13978999127', '4242424');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

-- 导出  表 fleamarket.car 结构
CREATE TABLE IF NOT EXISTS `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='购物车';

-- 正在导出表  fleamarket.car 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` (`id`, `user_id`, `product_id`, `number`) VALUES
	(9, 1, 5, 3),
	(10, 1, 2, 2),
	(11, 1, 1, 1),
	(12, 1, 6, 1);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;

-- 导出  表 fleamarket.flower_knowledge 结构
CREATE TABLE IF NOT EXISTS `flower_knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf32 COMMENT='养花知识';

-- 正在导出表  fleamarket.flower_knowledge 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `flower_knowledge` DISABLE KEYS */;
INSERT INTO `flower_knowledge` (`id`, `title`, `content`, `create_time`, `update_time`) VALUES
	(3, '认为认为人', '<p>热望认为人</p>', '2022-05-15 00:41:58', '2022-05-15 00:41:58');
/*!40000 ALTER TABLE `flower_knowledge` ENABLE KEYS */;

-- 导出  表 fleamarket.flower_language 结构
CREATE TABLE IF NOT EXISTS `flower_language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `re_time` timestamp NULL DEFAULT NULL COMMENT '展示日期',
  `title1` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '中文标语',
  `title2` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '英文标语',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf32 COMMENT='首页每日花语';

-- 正在导出表  fleamarket.flower_language 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `flower_language` DISABLE KEYS */;
INSERT INTO `flower_language` (`id`, `re_time`, `title1`, `title2`, `create_time`) VALUES
	(5, '2022-05-11 00:00:00', '认为人', '热污染', '2022-05-10 20:57:36'),
	(7, '2022-05-14 00:00:00', '我喜欢一些花儿，静静地开放，从不声张，有着清新得芬芳...', 'I love flowers, quietly open, never quiet. Little flowers, with the...', '2022-05-10 21:02:08');
/*!40000 ALTER TABLE `flower_language` ENABLE KEYS */;

-- 导出  表 fleamarket.message 结构
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `text` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品留言';

-- 正在导出表  fleamarket.message 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`id`, `user_id`, `product_id`, `text`, `create_time`) VALUES
	(3, 1, 1, '达到', '2022-04-26 22:34:30'),
	(4, 1, 1, '发发', '2022-04-26 22:35:15');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

-- 导出  表 fleamarket.notice 结构
CREATE TABLE IF NOT EXISTS `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COMMENT='通知、公告';

-- 正在导出表  fleamarket.notice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` (`id`, `title`, `content`, `create_time`, `update_time`) VALUES
	(1, '关于启用花店的通知', '<p><b>关于启用花店的通知</b></p><p><i>关于启用花店的通知</i></p><p><i>关于启用花店的通知</i></p><p><i>关于启用花店的通知</i></p><p><i>关于启用花店的通知</i></p>', '2022-04-05 21:17:11', '2022-05-28 19:26:20'),
	(2, '33', '<p>333发生发生</p>', '2022-04-17 21:48:11', '2022-05-28 19:26:25');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 导出  表 fleamarket.product 结构
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(4) DEFAULT NULL COMMENT '商品状态 0下架 1上架',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `sort` varchar(255) DEFAULT NULL COMMENT '分类',
  `des` text COMMENT '描述',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `old_price` decimal(10,2) DEFAULT NULL COMMENT '现价',
  `collect_num` int(11) DEFAULT NULL COMMENT '收藏数/购物车数量',
  `sally_num` int(11) DEFAULT NULL COMMENT '销量/初始化0',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品表';

-- 正在导出表  fleamarket.product 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `permission`, `number`, `title`, `sort`, `des`, `user_id`, `img_url`, `price`, `old_price`, `collect_num`, `sally_num`, `create_time`, `update_time`) VALUES
	(1, '1', 96, '梅花花一束', '测试', '梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束梅花花一束', NULL, '0.3247230865625157.jpg', 19.99, 999.99, 24, 4, '2022-05-03 14:35:23', '2022-05-03 15:57:38'),
	(2, '1', 32, '33', '2222', '3333333333333333', NULL, '0.08258521981829858.jpg', 33.00, 33.00, 3, 1, '2022-04-19 22:49:00', '2022-05-03 14:22:25'),
	(3, '1', 55, '44', '2222', '555', NULL, '0.4685209583685096.jpg', 55.00, 55.00, 0, 0, '2022-04-19 22:49:14', '2022-04-26 22:32:49'),
	(4, '1', 55, '555', '2222', '555', NULL, '0.8383768398574376.jpg', 55.00, 555.00, 0, 0, '2022-04-19 22:49:25', NULL),
	(5, '1', 55, '55', '2222', '5555555555', NULL, '0.8212508371197532.jpg', 55.00, 55.00, 6, 0, '2022-04-19 22:49:37', '2022-04-26 22:37:49'),
	(6, '1', 33, '33', '2222', '3333', NULL, '0.19003735162229607.jpg', 33.00, 33.00, 1, 0, '2022-04-19 22:51:35', '2022-04-26 22:42:11'),
	(7, '1', 33, '333', '测试', '3333', NULL, '0.05798194657368305.jpg', 33.00, 33.00, 0, 0, '2022-05-03 13:36:17', '2022-05-03 13:36:20');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 导出  表 fleamarket.product_type 结构
CREATE TABLE IF NOT EXISTS `product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COMMENT='商品类别';

-- 正在导出表  fleamarket.product_type 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` (`id`, `sort`, `name`, `create_time`) VALUES
	(3, 222, '2222', '2022-04-17 21:27:06'),
	(4, 333, '测试', '2022-04-17 21:37:11');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;

-- 导出  表 fleamarket.recommend 结构
CREATE TABLE IF NOT EXISTS `recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `re_time` timestamp NULL DEFAULT NULL COMMENT '推荐日期',
  `sort` int(11) DEFAULT NULL COMMENT '排序号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf32 COMMENT='每日推荐';

-- 正在导出表  fleamarket.recommend 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `recommend` DISABLE KEYS */;
INSERT INTO `recommend` (`id`, `product_id`, `re_time`, `sort`, `create_time`) VALUES
	(1, 1, '2022-05-15 00:00:00', 66, '2022-05-15 14:09:19'),
	(2, 2, '2022-05-15 00:00:00', 1, '2022-05-15 14:09:46'),
	(3, 5, '2022-05-15 00:00:00', 22, '2022-05-15 14:09:35'),
	(4, 1, '2022-05-15 00:00:00', 1, '2022-05-15 14:09:39'),
	(5, 7, '2022-05-15 00:00:00', NULL, '2022-05-15 14:09:50'),
	(6, 2, '2022-05-15 00:00:00', NULL, '2022-05-15 14:10:02'),
	(7, 2, '2022-05-15 00:00:00', NULL, '2022-05-15 14:10:07');
/*!40000 ALTER TABLE `recommend` ENABLE KEYS */;

-- 导出  表 fleamarket.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) DEFAULT NULL COMMENT '学号',
  `role` varchar(40) DEFAULT NULL COMMENT '角色',
  `permission` char(2) DEFAULT NULL COMMENT '授权（0代表未授权，1代表已授权）',
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `userName` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- 正在导出表  fleamarket.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `sno`, `role`, `permission`, `name`, `password`, `phone`, `email`, `create_time`, `update_time`) VALUES
	(1, 'Test12345', '管理员', '1', '测试1号', '0vhOi1/gVAyWjufOaXD9aGS+JgiABkWDmuxlK7cNAQ3kI09mItifJmod7kssCHCtdKQza+A9NKjUlj6mnfABcEIEsDv3qSKpB9iAEOyJUDfgxQvYpY8tzkKMZciz6LIXqM97k3r+8/ha8oLeN5sHeW6rXR4qSW21jG38ecWV8Ds=', '13978999127', '121@qq.com', '2022-04-05 21:08:40', '2022-04-25 22:03:36'),
	(2, '202', '普通用户', '1', '嘎嘎', 'Lae7w+6XQZoJCC61zw8MK8U4B7ShSemQRqguakZDqhxteqHGuT2g4j9JhizYq+7sHiLFAaL+35nJzvsTVaUjDMHf6piqCed018b+lf/xE9KN9vn885CKm//Bi3kCEqqs1Z1yRvqcIrfznST4CTMtXvNo1hUhggN1RR3eMt8HIXQ=', '13978999126', '1219881823@qq.com', '2022-05-03 13:31:29', '2022-05-11 15:39:57');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 fleamarket.user_notice 结构
CREATE TABLE IF NOT EXISTS `user_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `notice` int(11) DEFAULT NULL COMMENT '公告id',
  `is_read` int(11) DEFAULT NULL COMMENT '是否已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COMMENT='用户公告读取记录表';

-- 正在导出表  fleamarket.user_notice 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `user_notice` DISABLE KEYS */;
INSERT INTO `user_notice` (`id`, `user_id`, `notice`, `is_read`) VALUES
	(1, 1, 1, 1),
	(2, 1, 2, 1),
	(3, 2, 2, 1),
	(4, 2, 1, 1);
/*!40000 ALTER TABLE `user_notice` ENABLE KEYS */;

-- 导出  表 fleamarket.user_order 结构
CREATE TABLE IF NOT EXISTS `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `status` int(11) DEFAULT NULL COMMENT '订单状态, 1 已付款/待发货 3 已完成 2 已发货 0 已取消',
  `price` decimal(20,2) DEFAULT NULL COMMENT '订单总价',
  `addr_des` varchar(200) DEFAULT NULL COMMENT '地址详细',
  `addr_name` varchar(50) DEFAULT NULL COMMENT '地址收货人',
  `addr_phone` varchar(50) DEFAULT NULL COMMENT '地址电话',
  `account_record_id` int(11) DEFAULT NULL COMMENT '付款记录id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- 正在导出表  fleamarket.user_order 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` (`id`, `order_no`, `user_id`, `status`, `price`, `addr_des`, `addr_name`, `addr_phone`, `account_record_id`, `create_time`, `update_time`) VALUES
	(1, 'TEST0001', 1, 3, 19.99, '测试地址测试地址测试地址', '测试收货人', '13978999127', 1, '2022-04-05 21:14:58', '2022-04-05 21:14:58'),
	(2, 'TEST0001', 1, 0, 79.96, '测试地址测试地址测试地址', '测试收货人', '13978999127', 1, '2022-04-06 09:05:51', '2022-04-06 09:05:51'),
	(3, 'T16492076299201', 1, 0, 59.97, '测试地址测试地址测试地址', '测试收货人', '13978999127', 4, '2022-04-06 09:13:50', '2022-04-06 09:13:50'),
	(4, 'T16492078385461', 1, 0, 19.99, '测试地址测试地址测试地址', '测试收货人', '13978999127', 5, '2022-04-06 09:17:19', '2022-04-06 09:17:19'),
	(5, 'T16495919478061', 1, 0, 39.98, '测试地址测试地址测试地址', '测试收货人', '13978999127', 6, '2022-04-10 19:59:08', '2022-04-10 19:59:08'),
	(6, 'T16495931842861', 1, 0, 19.99, '测试地址测试地址测试地址', '测试收货人', '13978999127', 8, '2022-04-10 20:19:44', '2022-04-10 20:19:44'),
	(7, 'T16496037508681', 1, 0, 19.99, '测试地址测试地址测试地址', '测试收货人', '13978999127', 11, '2022-04-10 23:15:51', '2022-04-10 23:15:51'),
	(8, 'T16496038537531', 1, 0, 19.99, '测试地址测试地址测试地址', '测试收货人', '13978999127', 12, '2022-04-10 23:17:34', '2022-04-10 23:17:34'),
	(9, 'T16509818795701', 1, 1, 79.96, '测试地址测试地址测试地址', '测试收货人', '13978999127', 15, '2022-04-26 22:04:40', '2022-04-26 22:04:40'),
	(10, 'T16515590112462', 2, 1, 33.00, '4242424', '12', '13978999127', 17, '2022-05-03 14:23:31', '2022-05-03 14:23:31'),
	(11, 'T16515646728082', 2, 0, 179.91, '4242424', '12', '13978999127', 18, '2022-05-03 15:57:53', '2022-05-03 15:57:53');
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;

-- 导出  表 fleamarket.user_order_details 结构
CREATE TABLE IF NOT EXISTS `user_order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_price` decimal(20,2) DEFAULT NULL COMMENT '购买价格',
  `num` int(11) DEFAULT NULL COMMENT '购买总数量',
  `price` decimal(20,2) DEFAULT NULL COMMENT '购买总价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf32 COMMENT='订单明细';

-- 正在导出表  fleamarket.user_order_details 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `user_order_details` DISABLE KEYS */;
INSERT INTO `user_order_details` (`id`, `order_id`, `product_id`, `product_price`, `num`, `price`) VALUES
	(1, 1, 1, 19.99, 1, 19.99),
	(2, 1, 1, 19.99, 4, 79.96),
	(3, NULL, 1, 19.99, 3, 59.97),
	(4, 4, 1, 19.99, 1, 19.99),
	(5, 5, 1, 19.99, 2, 39.98),
	(6, 6, 1, 19.99, 1, 19.99),
	(7, 7, 1, 19.99, 1, 19.99),
	(8, 8, 1, 19.99, 1, 19.99),
	(9, 9, 1, 19.99, 4, 79.96),
	(10, 10, 2, 33.00, 1, 33.00),
	(11, 11, 1, 19.99, 9, 179.91);
/*!40000 ALTER TABLE `user_order_details` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
