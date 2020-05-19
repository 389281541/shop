# **商城项目**

- ## 项目介绍

  shop项目电商后端项目，和shop-portal-web、shop-admin-web构成前台商城系统及后台管理系统。后端项目基于Springcloud+MyBatisplus实现，前端项目基于vue+element-ui或iview实现。前台商城系统包含首页门户、用户注册登陆、用户信息管理、商品列表、商品详情展示、店铺信息展示、购物车、订单功能、商品促销、秒杀商品服务、积分系统、支付服务等模块。后台管理系统包含商品管理、类别管理、品牌管理、商铺管理、订单管理、促销活动管理、秒杀活动管理、优惠券管理和轮播图管理等模块。

  

- ## 项目代码：

后端项目  shop：https://github.com/389281541/shop.git

门户前端项目  shop-portal-web：https://github.com/389281541/shop-portal-web.git

管理前端项目 shop-admin-web：https://github.com/389281541/shop-admin-web.git



- ## 技术选型

后端技术：

| 技术        | 说明             |
| ----------- | ---------------- |
| springcloud | spring框架       |
| mybatisplus | ORM框架          |
| redis       | 分布式缓存       |
| Druid       | 数据库连接池     |
| JWT         | JWT登录支持      |
| Lombok      | 简化对象封装工具 |
| RabbitMq    | 消息队列         |
| Kaptcha     | 图形验证码框架   |
| Swagger-UI  | 文档生产工具     |

前端技术：

| 技术       | 说明             |
| ---------- | ---------------- |
| Vue        | 前端框架         |
| Vue-router | 路由框架         |
| Vuex       | 全局状态管理框架 |
| Element    | 前端UI框架       |
| iView      | 前端UI框架       |
| Axios      | 前端HTTP框架     |



- ## 模块介绍

### 后台管理系统

登录：

![](https://image.songshupinpin.com/a22daff982e7427c82812729a8d0e6cf)

商品管理：

![](https://image.songshupinpin.com/844d037620454367ae7c4e127d4a7899)

类别管理：

![](https://image.songshupinpin.com/43e503e8932340bf9eb7bfa83859c85a)

品牌管理：

![](https://image.songshupinpin.com/74354af7c63b4c2eb7ac93d3deb6c6c6)

商品属性管理：

![](https://image.songshupinpin.com/77ebf245be444f6aa2c9595226755652)

商铺管理：

![](https://image.songshupinpin.com/0d5bdcd9187d4d2fb8a3d7bd8b175c67)

订单管理：

![](https://image.songshupinpin.com/e35e14395e4c40cf908d3d9619576b34)

优惠券：

![](https://image.songshupinpin.com/4afc7e61841e4aed8c7ee33f64b5efd6)

秒杀管理：

![](https://image.songshupinpin.com/1de9486b050944f2933f93910917ceec)



### 前台商城系统

首页：

![](https://image.songshupinpin.com/f48cbf1f81f342ffa3b9a9f6f57759f7)

商品列表页：

![](https://image.songshupinpin.com/62ab8b5e931f486f92777d4cd46931dc)

商品详情页：

![](https://image.songshupinpin.com/865946109c9240d88b331cde6da9a69d)

购物车列表页：

![](https://image.songshupinpin.com/d0db4cc25bdd42b885ec12e27c81c3d6)

确认订单页：

![](https://image.songshupinpin.com/8d65dd6cba87450ca60e446de94f3aef)

支付跳转页：

![](https://image.songshupinpin.com/c38dcd3a395448658f9c9cbf2fae4597)



- ## 开发环境

| 工具     | 版本号  |
| -------- | ------- |
| jdk      | 1.8.25  |
| mysql    | 5.6.44  |
| redis    | 3.2.9   |
| rabbitmq | 3.1.5   |
| nodejs   | 10.16.0 |





- ## 本地环境搭建

1. 在本地安装好jdk、nodejs、redis、rabbitmq和mysql
2. 连接mysql执行table_create.sql脚本
3. 修改shop项目下admin和portal模块resource文件夹下application.yml配置文件中redis、rabbitmq和mysql的IP地址
4. 运行shop项目下的eureka模块
5. 运行shop项目下的admin和portal项目
6. 运行shop-portal-web项目和shop-admin-web项目
7. 在浏览器输入localhost:8800访问门户网站、输入localhost:8080访问管理后台