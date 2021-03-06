DROP TABLE IF EXISTS administrator;
CREATE TABLE vvshop_user.administrator
(
    id              bigint(20)   AUTO_INCREMENT         NOT NULL COMMENT '主键ID',
    user_name       varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户名',
    password        varchar(256) COLLATE utf8mb4_bin    NOT NULL COMMENT '密码',
    salt            varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '盐',
    mobile          varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户电话',
    avatar          varchar(512) COLLATE utf8mb4_bin    NULL COMMENT '用户头像',
    del_status      tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time     timestamp default NULL NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    last_login_time timestamp default NULL NULL COMMENT '上次登陆时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_user_name (user_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '后台管理用户表';

INSERT INTO vvshop_user.administrator VALUES (1, 'admin', 'b7f251619da8522e6880c1b74844c54c', 'e8iN9Zpb', '15801248054', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 0, '2019-06-20 20:05:52', '2019-06-20 20:05:46', '2019-06-20 20:05:53');
INSERT INTO vvshop_user.administrator VALUES (2, 'rainbow', '9275B6FA484E35F444705132ACD5B98F', 'ZaUoItV1', '15801248054', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 0, '2019-06-21 15:32:39', '2019-06-21 15:32:35', '2019-06-21 15:32:40');

DROP TABLE IF EXISTS role;
CREATE TABLE vvshop_user.role
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    name varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
    description varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色描述',
    status tinyint  default 1 NOT NULL COMMENT  '0-禁用 1-启用',
    update_time     timestamp default NULL NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_role_name (name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '角色表';

INSERT INTO vvshop_user.role VALUES (1, 'ADMIN', '超级管理员', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vvshop_user.role VALUES (2, 'GOODS_MANAGER', '商品管理员', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vvshop_user.role VALUES (3, 'BRAND_MANAGER', '品牌管理员', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vvshop_user.role VALUES (4, 'ORDER_MANAGER', '订单管理员', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO vvshop_user.role VALUES (5, 'FLOW_MANAGER', '物流管理员', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


DROP TABLE IF EXISTS permission;
CREATE TABLE vvshop_user.permission
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    pid bigint(20) DEFAULT NULL COMMENT '父级权限id',
    name varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
    value varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限值',
    type tinyint NOT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
    uri varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '前端资源路径',
    status tinyint DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
    update_time     timestamp default NULL NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '权限表';

/*首页*/
INSERT INTO vvshop_user.permission VALUES (1, 0, '首页', null, 0, null, 1, CURRENT_TIME, CURRENT_TIME);
/*类别管理*/
INSERT INTO vvshop_user.permission VALUES (2, 0, '类别管理', null, 0, null, 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (3, 2, '添加分类', 'pms:item:add', 1, '/item/add', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (4, 2, '分类列表', 'pms:item:pageParent', 1, '/item/pageParent', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (5, 4, '子类分类列表', 'pms:item:pageSub', 2, '/item/pageSub', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (6, 4, '修改商品分类', 'pms:item:update', 2, '/item/update', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (7, 4, '删除商品分类', 'pms:item:remove', 2, '/item/delete', 1, CURRENT_TIME, CURRENT_TIME);

/*品牌管理*/
INSERT INTO vvshop_user.permission VALUES (8, 0, '品牌管理', null, 0, null, 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (9, 8, '品牌列表', 'pms:brand:page', 1, '/brand/page', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (10, 8, '添加品牌', 'pms:brand:add', 1, '/brand/add', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (11, 9, '品牌详情', 'pms:brand:detail', 2, '/brand/detail', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (12, 9, '修改品牌', 'pms:brand:update', 2, '/brand/update', 1, CURRENT_TIME, CURRENT_TIME);
INSERT INTO vvshop_user.permission VALUES (13, 9, '删除品牌', 'pms:brand:remove', 2, '/brand/remove', 1, CURRENT_TIME, CURRENT_TIME);


DROP TABLE IF EXISTS administrator_role;
CREATE TABLE vvshop_user.administrator_role
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    admin_id bigint(20) NOT NULL COMMENT '管理用户ID',
    role_id bigint(20) NOT NULL COMMENT '角色ID',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '用户和角色关联表';

INSERT INTO vvshop_user.administrator_role VALUES (1, 1, 1, CURRENT_TIME);

DROP TABLE IF EXISTS role_permission;
CREATE TABLE vvshop_user.role_permission
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    role_id bigint(20) NOT NULL COMMENT '角色ID',
    permission_id bigint(20) NOT NULL COMMENT '权限ID',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '用户和权限关联表';

INSERT INTO  vvshop_user.role_permission values (1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (2, 1, 2, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (3, 1, 3, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (4, 1, 4, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (5, 1, 5, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (6, 1, 6, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (7, 1, 7, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (8, 1, 8, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (9, 1, 9, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (10, 1, 10, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (11, 1, 11, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (12, 1, 12, CURRENT_TIMESTAMP);
INSERT INTO  vvshop_user.role_permission values (13, 1, 13, CURRENT_TIMESTAMP);

DROP TABLE IF EXISTS vvshop_user.customer;
CREATE TABLE vvshop_user.customer
(
    id                  bigint(20)   AUTO_INCREMENT         NOT NULL COMMENT '主键ID',
    user_name           varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户名',
    password            varchar(256) COLLATE utf8mb4_bin    NOT NULL COMMENT '密码',
    salt                varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '盐',
    mobile              varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户电话',
    avatar              varchar(512) COLLATE utf8mb4_bin    NULL COMMENT '用户头像',
    identity_name       varchar(512) COLLATE utf8mb4_bin    NULL COMMENT '真实姓名',
    identity_type       tinyint      NULL DEFAULT 1 COMMENT '证件类型：1 身份证，2 军官证，3 护照',
    identity_no         varchar(20)  NULL COMMENT '证件号码',
    email               varchar(50)  NULL COMMENT '邮箱',
    gender              tinyint(1)   NOT NULL COMMENT '性别: 0-男 1-女',
    birthday            timestamp    NULL COMMENT '用户生日',
    integration         int(11)     NOT NULL DEFAULT 0 COMMENT '积分',
    user_status     tinyint   default 0                 NOT NULL COMMENT '用户状态 0-正常 1-禁用',
    update_time     timestamp default NULL NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    last_login_time timestamp default NULL NULL COMMENT '上次登陆时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_user_name (user_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '顾客登录表';

CREATE TABLE vvshop_user.customer_address
(
    id                  bigint(20)   AUTO_INCREMENT         NOT NULL COMMENT '主键ID',
    customer_id         bigint(20)  NOT NULL COMMENT '用户ID',
    receiver_name       varchar(64) NOT NULL COMMENT '收货人姓名',
    zip                 varchar(64)  NULL COMMENT '邮编',
    province            varchar(64)    NOT NULL COMMENT '地区表中省份',
    city                varchar(64)    NOT NULL COMMENT '地区表中城市',
    district            varchar(64)    NOT NULL COMMENT '地区表中的区',
    address             varchar(200) NOT NULL COMMENT '具体的地址门牌号',
    phone             varchar(256) NOT NULL COMMENT '收货人手机号',
    is_default          tinyint default 0 NOT NULL COMMENT '是否默认 0-否 1-是',
    del_status      tinyint   default 0                 NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time     timestamp default NULL NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '顾客地址表';

CREATE TABLE vvshop_user.customer_level
(
   id          bigint(20)   AUTO_INCREMENT   NOT NULL COMMENT '主键ID',
   level_name  varchar(10) NOT NULL COMMENT '会员级别名称',
   min_point INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '该级别最低积分',
   max_point INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '该级别最高积分',
   update_time     timestamp default NULL NULL COMMENT '更新时间',
   create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
   PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户会员级别信息表';

DROP TABLE vvshop_goods.brand;
CREATE TABLE vvshop_goods.brand
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    name varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '品牌名称',
    logo  varchar(256) COLLATE utf8mb4_bin NULL COMMENT '品牌logo',
    description  varchar(1024) COLLATE utf8mb4_bin NULL COMMENT '品牌描述',
    del_status tinyint default 0  NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_brand_name (name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '品牌表';

INSERT INTO vvshop_goods.brand VALUES (1, '卡尼尔', NULL, '值得信赖的化妆品牌', 0, NULL, CURRENT_TIMESTAMP);
INSERT INTO vvshop_goods.brand VALUES (2, '舒肤佳', NULL, '洗漱用品', 0, NULL, CURRENT_TIMESTAMP);
INSERT INTO vvshop_goods.brand VALUES (3, '海飞丝', NULL, '洗头发必备神器', 0, NULL, CURRENT_TIMESTAMP);
INSERT INTO vvshop_goods.brand VALUES (4, '资深堂', NULL, '清爽去屑防脱不伤头皮', 0, NULL, CURRENT_TIMESTAMP);
INSERT INTO vvshop_goods.brand VALUES (5, '霸王', NULL, '据说防脱很有效果', 0, NULL, CURRENT_TIMESTAMP);
INSERT INTO vvshop_goods.brand VALUES (6, '黑人', NULL, '这款牙膏口气清新，效果很好', 0, NULL, CURRENT_TIMESTAMP);
INSERT INTO vvshop_goods.brand VALUES (7, '佳洁士', NULL, '清新口气，保护牙龈', 0, NULL, CURRENT_TIMESTAMP);
INSERT INTO vvshop_goods.brand VALUES (8, '雕牌', NULL, '中国清洁驰名商标', 0, NULL, CURRENT_TIMESTAMP);


DROP TABLE vvshop_goods.brand_item;
CREATE TABLE vvshop_goods.brand_item
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    brand_id bigint(20) NOT NULL COMMENT '品牌ID',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    sort_id bigint(20) NOT NULL COMMENT '排序ID',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '品牌种类关联表';

INSERT INTO vvshop_goods.brand_item values (1, 1, 9, 1, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (2, 1, 11, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (3, 1, 14, 1, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (4, 2, 28, 1, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (5, 2, 32, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (6, 3, 20, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (7, 3, 21, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (8, 4, 29, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (9, 4, 27, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (10, 5, 20, 2,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (11, 5, 21, 2,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (12, 6, 36, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (13, 6, 38, 1,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (14, 7, 36, 2,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (15, 8, 28, 2,  NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.brand_item values (16, 8, 29, 2,  NULL, CURRENT_TIMESTAMP());

DROP TABLE  vvshop_goods.item;
CREATE TABLE vvshop_goods.item
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '类别名称',
    item_no bigint(20) NOT NULL COMMENT '编号',
    parent_id bigint(20) default NULL NULL COMMENT '父类别ID',
    sort_id bigint(20) NOT NULL COMMENT '排序ID',
    is_parent tinyint(1) NOT NULL comment '是否父级别 0-非父级别 1-父级别',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '类别表';

INSERT INTO vvshop_goods.item VALUES (1, '面部护肤', 1001, null, 1, 1, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (2, '洗发护发', 1002, null, 2, 1, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (3, '身体护理', 1003, null, 3, 1, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (4, '口腔护理', 1004, null, 4, 1, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (5, '女性护理', 1005, null, 5, 1, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (6, '香水彩妆', 1006, null, 6, 1, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (7, '清洁用品', 1007, null, 7, 1, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (8, '宠物生活', 1008, null, 8, 1, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (9, '补水保湿',  1009, 1, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (10, '卸妆',    1010, 1, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (11, '洁面',    1011, 1, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (12, '爽肤水',   1012, 1, 4, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (13, '乳液面霜', 1013, 1, 5, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (14, '精华',     1014, 1, 6, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (15, '眼霜',     1015, 1, 7, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (16, '防嗮',     1016, 1, 8, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (17, '面膜',     1017, 1, 9, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (18, '剃须',     1018, 1, 10, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (19, '套装',     1019, 1, 11, 0, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (20, '洗发',    1020, 2, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (21, '护发',    1021, 2, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (22, '染发',    1022, 2, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (23, '造型',    1023, 2, 4, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (24, '假发',    1024, 2, 5, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (25, '美发工具', 1025, 2, 6, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (26, '套装',    1026, 2, 7, 0, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (27, '补水保湿',    1027, 3, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (28, '沐浴',        1028, 3, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (29, '润肤',        1029, 3, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (30, '精油',       1030, 3, 4, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (31, '颈部',       1031, 3, 5, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (32, '手足',       1032, 3, 6, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (33, '纤体塑形',    1033, 3, 7, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (34, '美胸',       1034, 3, 8, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (35, '套装',       1035, 3, 9, 0, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (36, '牙膏/牙粉',    1036, 4, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (37, '牙刷/牙线',    1037, 4, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (38, '漱口水',       1038, 4, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (39, '套装',         1039, 4, 4, 0, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (40, '卫生巾',    1040, 5, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (41, '卫生护垫',   1041, 5, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (42, '私密护理',   1042, 5, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (43, '脱毛膏',    1043, 5, 4, 0, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (69, 'BB霜',     1069, 6, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (45, '女士香水',   1045, 6, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (46, '男士香水',   1046, 6, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (47, '底妆',      1047, 6, 4, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (48, '眉笔',      1048, 6, 5, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (49, '睫毛膏',    1049, 6, 6, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (50, '眼线',     1050, 6, 7, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (51, '眼影',     1051, 6, 8, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (52, '唇膏/彩',   1052, 6, 9, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (44, '化妆棉',     1044, 6, 10, 0, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (53, '纸品湿巾',   1053, 7, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (54, '衣物清洁',   1054, 7, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (55, '清洁工具',   1055, 7, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (56, '家庭清洁',   1056, 7, 4, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (57, '一次性用品',  1057, 7, 5, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (58, '驱虫用品',   1058, 7, 6, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (59, '皮具护理',   1059, 7, 7, 0, 0, NULL, CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (60, '水族世界',   1060, 8, 1, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (61, '狗粮',      1061, 8, 2, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (62, '猫粮',      1062, 8, 3, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (63, '猫狗罐头',   1063, 8, 4, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (64, '狗零食',     1064, 8, 5, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (65, '猫零食',     1065, 8, 6, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (66, '医疗保健',   1066, 8, 7, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (67, '宠物玩具',   1067, 8, 8, 0, 0, NULL, CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (68, '宠物服饰',   1068, 8, 9, 0, 0, NULL, CURRENT_TIMESTAMP());



CREATE TABLE vvshop_goods.spec_name
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '属性名称',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    exist_alias tinyint(1) default 0 NOT NULL COMMENT '是否存在别名 0-不存在 1-存在',
    is_color tinyint(1) default 0 NOT NULL COMMENT '是否颜色属性 0-不是 1-是',
    is_enum tinyint(1) default 0 NOT NULL COMMENT '是否枚举属性 0-不是 1-是',
    is_input tinyint(1) default 0 NOT NULL COMMENT '是否输入属性 0-不是 1-是',
    is_critical tinyint(1) default 0 NOT NULL COMMENT '是否关键属性 0-不是 1-是',
    is_sku tinyint(1) default 0 NOT NULL COMMENT '是否销售属性 0-不是 1-是',
    is_search tinyint(1) default 0 NOT NULL COMMENT '是否搜索字段 0-不是 1-是',
    is_must  tinyint(1) default 0 NOT NULL COMMENT '是否必须 0-不是 1-是',
    is_multiple  tinyint(1) default 0 NOT NULL COMMENT '是否多选 0-不是 1-是',
    type tinyint(1) DEFAULT 0 COMMENT '属性的类型；0->规格；1->参数',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '规格名称表';


CREATE TABLE vvshop_goods.spec_value
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    spec_name_id bigint(20) NOT NULL COMMENT '属性名ID',
    spec_value varchar(128) NOT NULL COMMENT '属性值',
    sort_id int(11) NOT NULL COMMENT '排序ID',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '规格值表';

DROP TABLE vvshop_goods.shop;
CREATE TABLE vvshop_goods.shop
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '店铺名称',
    logo  varchar(256) NULL DEFAULT NULL COMMENT '店铺logo',
    type TINYINT NOT NULL COMMENT '店铺类型：1.自营，2.平台',
    keeper_name VARCHAR(50) NOT NULL COMMENT '店主姓名',
    phone_number VARCHAR(50) NOT NULL COMMENT '联系电话',
    bank_name VARCHAR(50) NOT NULL COMMENT '供应商开户银行名称',
    bank_account VARCHAR(50) NOT NULL COMMENT '银行账号',
    address VARCHAR(200) NULL COMMENT '店铺地址(默认发货地址)',
    audit_status TINYINT NOT NULL DEFAULT 1 COMMENT '状态 0未通过，1审核中 2审核通过',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    register_time timestamp default NULL NULL COMMENT '注册时间',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY  (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺表';


DROP TABLE vvshop_goods.spu;
CREATE TABLE vvshop_goods.spu
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
    spu_no varchar(256) NOT NULL COMMENT '商品编号',
    brand_id bigint(20) NOT NULL COMMENT '品牌ID',
    brand_name varchar(1000) NOT NULL COMMENT '品牌名称',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    item_name varchar(1000) NOT NULL COMMENT '类别名称',
    shop_id bigint(20) NOT NULL COMMENT '店铺ID',
    shop_name varchar(1000) NOT NULL COMMENT '店铺名称',
    sale    int(11) DEFAULT 0 COMMENT '销量',
    unit varchar(16) DEFAULT NULL COMMENT '单位',
    description varchar(1000) DEFAULT NULL COMMENT '商品描述',
    sale_status TINYINT NOT NULL DEFAULT 0 COMMENT '上下架状态 0下架 1上架',
    audit_status TINYINT NOT NULL DEFAULT 1 COMMENT '审核状态：0审核拒绝，1正在审核，2审核通过',
    sort_id int(11) NOT NULL COMMENT 'spu排序',
    recommend TINYINT NOT NULL DEFAULT 0 COMMENT '是否推荐 0不推荐 1推荐',
    use_integration_limit int(11) DEFAULT NULL COMMENT '限制使用的积分数',
    promotion_start_time timestamp default NULL NULL COMMENT '促销开始时间',
    promotion_end_time timestamp default NULL NULL COMMENT '促销结束时间',
    promotion_per_limit int(11) DEFAULT NULL COMMENT '活动限购数量',
    promotion_type int(1) DEFAULT NULL COMMENT '促销类型：0->没有促销使用原价;1->使用促销价；2->使用满减价格；',
    min_price decimal(20,2) DEFAULT NULL COMMENT '相应sku中最低价格',
    del_status tinyint default 0 NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品表';

DROP TABLE vvshop_goods.spu_img;
CREATE TABLE vvshop_goods.spu_img
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    spu_id bigint(20) NOT NULL COMMENT 'SPU ID',
    sku_id bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
    img_url varchar(256) NULL COMMENT '图片地址',
    is_cover tinyint NOT NULL DEFAULT 0 COMMENT '是否封面图 0-非封面图 1-封面图',
    is_master tinyint NOT NULL DEFAULT 0 COMMENT '是否主图 0-非主图 1-主图',
    is_color tinyint NOT NULL DEFAULT 0 COMMENT '是否颜色图 0-非颜色图 1-颜色图',
    sort_id int(11) NOT NULL COMMENT '图片位置',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品图片表';



CREATE TABLE vvshop_goods.spu_spec
(
    id            bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    spu_id    bigint(20)                          NOT NULL COMMENT '商品ID',
    spec_name_id  bigint(20)                          NOT NULL COMMENT '属性名ID',
    spec_name      varchar(256)                     NOT NULL COMMENT '属性名',
    spec_value_id bigint(20)                        NULL COMMENT '属性值ID',
    spec_value varchar(256)                         NULL COMMENT '属性名',
    update_time   timestamp default NULL NULL COMMENT '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品基本属性表';

DROP TABLE vvshop_goods.sku;
CREATE TABLE vvshop_goods.sku
(
    id            bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    sku_name      varchar(256) NOT NULL COMMENT 'SKU名称',
    sku_no       bigint(20)  NULL COMMENT '商品编码',
    spu_id        bigint(20)  NULL COMMENT '商品ID',
    stock         int(11)  NULL COMMENT '库存',
    low_stock     int(11)  NULL COMMENT '预警库存',
    lock_stock    int(11)  DEFAULT 0 COMMENT '锁定库存',
    sale          int(11) DEFAULT 0 COMMENT '销量',
    weight        decimal(10,2) DEFAULT NULL COMMENT '商品重量，默认为克',
    dimension     tinyint DEFAULT NULL COMMENT '尺寸：0-小件，1-中件，2-大件',
    original_price decimal(20, 2)  NULL COMMENT '价格',
    price         decimal(20, 2)  NULL COMMENT '价格',
    del_status    tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time   timestamp default NULL NULL COMMENT '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品sku表';




CREATE TABLE vvshop_goods.sku_spec
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    sku_id              bigint(20) NOT NULL COMMENT 'SKUID',
    spec_name_id        bigint(20) NOT NULL COMMENT '属性名ID',
    spec_name           varchar(1024) NOT NULL COMMENT '属性名',
    spec_value_id       bigint(20) NOT NULL COMMENT '属性值ID',
    spec_value          bigint(20) NOT NULL COMMENT '属性值',
    sort_id             int(11) NULL COMMENT '排序ID',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment 'sku属性关联表';




CREATE TABLE vvshop_goods.cart
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    sku_id              bigint(20) NOT NULL COMMENT 'SKUID',
    sku_spec            varchar(1024) DEFAULT NULL COMMENT 'sku属性',
    sku_stock           int(11)  NULL COMMENT '库存',
    spu_id              bigint(20) NOT NULL COMMENT 'SPUID',
    spu_name            varchar(256) NULL COMMENT '商品名称',
    cover_img           varchar(256) NULL COMMENT '封面图片',
    customer_id         bigint(20) NOT NULL COMMENT '用户ID',
    shop_id             bigint(20) NOT NULL COMMENT '店铺ID',
    item_id             bigint(20) NOT NULL COMMENT '类别ID',
    quantity            int(11) NOT NULL COMMENT '商品数量',
    price               decimal(10,2) NOT NULL COMMENT '价格',
    del_status          tinyint   default 0  NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '购物车表';


CREATE TABLE vvshop_goods.order_
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    order_no            varchar(256) NOT NULL COMMENT '订单编号',
    customer_id         bigint(20) NOT NULL COMMENT '用户ID',
    customer_name       varchar(256) NULL COMMENT '用户名称',
    pay_type            tinyint   default NULL NULL COMMENT '支付方式 0：支付宝，1：微信，2：银行卡',
    status              tinyint   default 0 NOT NULL COMMENT '订单状态 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
    order_type          tinyint default NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
    auto_confirm_day   int(11) DEFAULT NULL COMMENT '自动确认时间（单位为天）',
    trade_no            varchar(256) default NULL NULL COMMENT '支付交易号',
    promotion_amount    bigint(20) NOT NULL COMMENT '促销金额',
    integration_amount  bigint(20) NOT NULL COMMENT '积分金额',
    promotion_info      varchar(100) DEFAULT NULL COMMENT '活动信息',
    integration_award   int(11) DEFAULT NULL COMMENT '该订单可以获得的积分',
    coupon_amount       bigint(20) NOT NULL COMMENT '优惠券金额',
    total_amount        bigint(20) NOT NULL COMMENT '支付金额',
    flow_id             bigint(20) NULL COMMENT '物流ID',
    delivery_company    varchar(128) DEFAULT NULL COMMENT '物流公司(配送方式)',
    delivery_bill_no    varchar(64) DEFAULT NULL COMMENT '物流单号',
    deliver_mode        tinyint default 0 NULL COMMENT '配送方式：0-快递 1-自取',
    deliver_fee         bigint(20) NOT NULL COMMENT '运费',
    receiver_name       varchar(100) NOT NULL COMMENT '收货人姓名',
    receiver_phone      varchar(32) NOT NULL COMMENT '收货人电话',
    receiver_post_code  varchar(32) DEFAULT NULL COMMENT '收货人邮编',
    receiver_province   varchar(32) DEFAULT NULL COMMENT '省份/直辖市',
    receiver_city       varchar(32) DEFAULT NULL COMMENT '城市',
    receiver_region     varchar(32) DEFAULT NULL COMMENT '区',
    receiver_detail_address varchar(200) DEFAULT NULL COMMENT '详细地址',
    note                varchar(500) DEFAULT NULL COMMENT '订单备注',
    confirm_status      int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
    use_integration     int(11) DEFAULT NULL COMMENT '下单时使用的积分',
    use_coupon_id       int(11) DEFAULT NULL COMMENT '下单时使用的优惠券',
    payment_time      timestamp DEFAULT NULL NULL COMMENT '支付时间',
    delivery_time timestamp DEFAULT NULL NULL COMMENT '发货时间',
    receive_time timestamp DEFAULT NULL NULL COMMENT '确认收货时间',
    p_order_no varchar(256) DEFAULT NULL COMMENT '父订单号',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '订单表';

DROP TABLE IF EXISTS order_setting;
CREATE TABLE order_setting (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  flash_order_overtime int(11) DEFAULT NULL COMMENT '秒杀订单超时关闭时间(分)',
  normal_order_overtime int(11) DEFAULT NULL COMMENT '正常订单超时时间(分)',
  confirm_overtime int(11) DEFAULT NULL COMMENT '发货后自动确认收货时间（天）',
  finish_overtime int(11) DEFAULT NULL COMMENT '自动完成交易时间，不能申请售后（天）',
  comment_overtime int(11) DEFAULT NULL COMMENT '订单完成后自动好评时间（天）',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='订单设置表';

CREATE TABLE vvshop_goods.order_sku
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    order_id             bigint(20) DEFAULT NULL COMMENT '订单id',
    order_no            varchar(256) NOT NULL COMMENT '订单编号',
    sku_id              bigint(20) NOT NULL COMMENT 'SKUID',
    spu_id              bigint(20) NOT NULL COMMENT 'SPUID',
    sku_pic             varchar(500) DEFAULT NULL COMMENT 'SPU图片',
    sku_name            varchar(500) DEFAULT NULL COMMENT 'SKU名称',
    sku_spec            varchar(1024) DEFAULT NULL COMMENT 'sku属性JSON',
    brand_id            bigint(20) NOT NULL COMMENT '品牌ID',
    brand_name          varchar(20) NOT NULL COMMENT '品牌名称',
    shop_id             bigint(20) NOT NULL COMMENT '店铺ID',
    quantity            int(11) NOT NULL COMMENT '购买数量',
    price               bigint(20) NOT NULL COMMENT '价格',
    item_id             bigint(20) NOT NULL COMMENT '类别ID',
    promotion_name      varchar(200) DEFAULT NULL COMMENT '商品促销名称',
    promotion_amount    decimal(10,2) DEFAULT NULL COMMENT '商品促销分解金额',
    coupon_amount       decimal(10,2) DEFAULT NULL COMMENT '优惠券优惠分解金额',
    integration_amount  decimal(10,2) DEFAULT NULL COMMENT '积分优惠分解金额',
    real_amount         decimal(10,2) DEFAULT NULL COMMENT '该商品经过优惠后的分解金额',
    integration_award   int(11) DEFAULT 0,
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '订单-sku关联表';

CREATE TABLE vvshop_goods.integration_rule_setting (
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    deduction_per_yuan  int(11) DEFAULT NULL COMMENT '每块元需要抵扣的积分数',
    max_percent_per_order int(11) DEFAULT NULL COMMENT '每笔订单最高抵用百分比',
    use_unit int(11) DEFAULT NULL COMMENT '使用积分最小单位100',
    coupon_status int(1) DEFAULT NULL COMMENT '是否可以和优惠券同用；0->不可以；1->可以',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '积分规则配置表';

INSERT INTO vvshop_goods.integration_rule_setting VALUES (1, 100, 20, 100, 1);

DROP TABLE IF EXISTS coupon;
CREATE TABLE vvshop_goods.coupon (
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    type                tinyint DEFAULT NULL COMMENT '优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券',
    name                varchar(256) DEFAULT NULL COMMENT '优惠券名称',
    totalNum            int(11) DEFAULT NULL COMMENT '总数量',
    amount              decimal(10,2) DEFAULT NULL COMMENT '金额',
    receive_num_limit   int(11) DEFAULT NULL COMMENT '每人限领张数',
    use_condition_type  decimal(10,2) DEFAULT NULL COMMENT '使用门槛；0表示无门槛 1-满多少金额使用',
    use_condition_limit int(11) DEFAULT NULL COMMENT '使用条件的数量金额限制,金额时精确到分',
    start_effect_time   timestamp NOT NULL COMMENT '优惠券有效期开始时间',
    end_effect_time     timestamp NOT NULL COMMENT '优惠券有效期截止时间',
    scope_type          tinyint DEFAULT NULL COMMENT '使用类型：0->全场通用；1->指定分类；2->指定商品',
    note                varchar(200) DEFAULT NULL COMMENT '备注',
    publish_num         int(11) DEFAULT NULL COMMENT '发行数量',
    use_num             int(11) DEFAULT NULL COMMENT '已使用数量',
    receive_num         int(11) DEFAULT NULL COMMENT '领取数量',
    enable_time         datetime DEFAULT NULL COMMENT '可以领取的日期',
    coupon_code                varchar(64) DEFAULT NULL COMMENT '优惠码',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';


DROP TABLE IF EXISTS coupon_customer;
CREATE TABLE vvshop_goods.coupon_customer (
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    coupon_id           bigint(20) DEFAULT NULL COMMENT '优惠券ID',
    customer_id         bigint(20) DEFAULT NULL COMMENT '用户ID',
    coupon_code         varchar(64) DEFAULT NULL COMMENT '优惠券码',
    customer_name       varchar(64) DEFAULT NULL COMMENT '领取人昵称',
    receive_type        tinyint DEFAULT NULL COMMENT '获取类型：0->后台赠送；1->主动获取',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    use_status          tinyint DEFAULT NULL COMMENT '使用状态：0->未使用；1->已使用；2->已过期',
    use_time            timestamp DEFAULT 0 COMMENT '使用时间',
    order_id            bigint(20) DEFAULT NULL COMMENT '订单编号',
    order_no            varchar(100) DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (id),
  KEY idx_customer_id (customer_id) USING BTREE,
  KEY idx_coupon_id (coupon_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券使用、领取历史表';

DROP TABLE IF EXISTS coupon_item;
CREATE TABLE coupon_item (
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    coupon_id           bigint(20) DEFAULT NULL COMMENT '优惠券ID',
    item_id bigint(20) DEFAULT NULL COMMENT '类别ID',
    item_name varchar(200) DEFAULT NULL COMMENT '产品分类名称',
    parent_item_name varchar(200) DEFAULT NULL COMMENT '父分类名称',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券和商品分类关系表';

DROP TABLE IF EXISTS coupon_spu;
CREATE TABLE coupon_spu (
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    coupon_id           bigint(20) DEFAULT NULL COMMENT '优惠券ID',
    spu_id              bigint(20) DEFAULT NULL COMMENT 'SPUID',
    spu_no              varchar(500) DEFAULT NULL COMMENT 'SPU编号',
    spu_name            varchar(500) DEFAULT NULL COMMENT '商品名称',
    receive_type        tinyint DEFAULT NULL COMMENT '获取类型：0->后台赠送；1->主动获取 2-分享链接',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券和商品的关系表';


DROP TABLE IF EXISTS flash_promotion;
CREATE TABLE flash_promotion (
  id            bigint(20) NOT NULL AUTO_INCREMENT,
  title         varchar(200) DEFAULT NULL,
  start_date    date DEFAULT NULL COMMENT '开始日期',
  end_date      date DEFAULT NULL COMMENT '结束日期',
  status        int(1) DEFAULT NULL COMMENT '上下线状态',
  create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time         timestamp default NULL NULL COMMENT '更新时间',
  del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='秒杀促销';

DROP TABLE IF EXISTS flash_promotion_spu;
CREATE TABLE vvshop_goods.flash_promotion_spu (
  id                        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  flash_promotion_id        bigint(20) DEFAULT NULL COMMENT '秒杀活动ID',
  flash_promotion_session_id bigint(20) DEFAULT NULL COMMENT '编号',
  spu_id                    bigint(20) DEFAULT NULL COMMENT '商品ID',
  sku_id                    bigint(20) DEFAULT NULL COMMENT 'SKUID',
  sku_no                    varchar(256) DEFAULT NULL COMMENT 'sku编号',
  sku_stock                 bigint(20) DEFAULT NULL COMMENT 'sku库存',
  flash_price               decimal(10,2) DEFAULT NULL COMMENT '限时购价格',
  original_price            decimal(10,2) DEFAULT NULL COMMENT '商品原价格',
  flash_promotion_num       int(11) DEFAULT NULL COMMENT '限时购数量',
  flash_promotion_limit     int(11) DEFAULT NULL COMMENT '每人限购数量',
  sort_id                   int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品限时购与商品关系表';

DROP TABLE IF EXISTS flash_promotion_session;
CREATE TABLE flash_promotion_session (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  name varchar(200) DEFAULT NULL COMMENT '场次名称',
  start_time time DEFAULT NULL COMMENT '每日开始时间',
  end_time time DEFAULT NULL COMMENT '每日结束时间',
  status int(1) DEFAULT NULL COMMENT '启用状态：0->不启用；1->启用',
  create_time    timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time         timestamp default NULL NULL COMMENT '更新时间',
  del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='限时购场次表';

CREATE TABLE vvshop_goods.flow
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    order_no            bigint(20) NOT NULL COMMENT '订单编号',
    company_id          bigint(20) NOT NULL COMMENT '物流公司ID',
    flow_no             bigint(20) NULL COMMENT '快递单号',
    send_time           timestamp default NULL NULL COMMENT '发货时间',
    arrival_time        timestamp default NULL NULL COMMENT '到达时间',
    receiver_address    varchar(256)  NOT NULL COMMENT '收货地址',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '物流表';


CREATE TABLE vvshop_goods.flow_logistics
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    order_no            bigint(20) NOT NULL COMMENT '订单编号',
    flow_status         tinyint   default 0 NOT NULL COMMENT '状态 0-待揽件 1-运输  3-派送',
    staff               varchar(50)  NOT NULL COMMENT '录入人',
    phone               varchar(50)  NOT NULL COMMENT '电话',
    remark              varchar(256)  NOT NULL COMMENT '备注信息',
    address             varchar(256)  NOT NULL COMMENT '地点',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '物流记录表';



CREATE TABLE vvshop_goods.flow_company
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    name                varchar(256)  COMMENT '物流公司名称',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '物流公司列表';


CREATE TABLE vvshop_goods.payment_record
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    customer_id         bigint(20) NULL COMMENT '用户ID',
    p_order_no          varchar(256) NOT NULL COMMENT '父订单编号',
    trade_no            varchar(256) NULL COMMENT '交易流水号',
    trade_status        varchar(256) NULL COMMENT '交易状态',
    pay_platform        tinyint   default NULL NULL COMMENT '支付平台 0：支付宝，1：微信',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '支付记录表';



CREATE TABLE vvshop_goods.order_comment
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    sku_id              bigint(20) NOT NULL COMMENT 'SKUID',
    spu_id              bigint(20) NOT NULL COMMENT 'SPUID',
    customer_id         bigint(20) NOT NULL COMMENT '用户ID',
    user_name           varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户名',
    thumbs_up           bigint(20) NOT NULL COMMENT '点赞数',
    content             varchar(2048)  NOT NULL COMMENT '备注信息',
    score               smallint  NOT NULL COMMENT '评分',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品评价';


CREATE TABLE vvshop_goods.order_return
(
    id bigint(20)  AUTO_INCREMENT,
    type tinyint NOT NULL COMMENT '类型：0-退款 1-退货退款',
    shop_id bigint(20) DEFAULT NULL COMMENT '店铺ID',
    shop_address varchar(256) DEFAULT NULL COMMENT '收货地址表',
    receiver_phone varchar(256) DEFAULT NULL COMMENT '收货人',
    receiver_name varchar(256) DEFAULT NULL COMMENT '收货人名称',
    order_id bigint(20) DEFAULT NULL COMMENT '订单id',
    order_no varchar(64) DEFAULT NULL COMMENT '订单编号',
    create_time datetime DEFAULT NULL COMMENT '申请时间',
    user_name varchar(64) DEFAULT NULL COMMENT '退货人姓名',
    user_phone varchar(100) DEFAULT NULL COMMENT '退货人电话',
    return_amount decimal(10,2) DEFAULT NULL COMMENT '退款金额',
    status int(1) DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
    handle_time datetime DEFAULT NULL COMMENT '处理时间',
    pay_price decimal(10,2) DEFAULT NULL COMMENT '订单实际支付价格',
    reason varchar(200) DEFAULT NULL COMMENT '原因',
    description varchar(500) DEFAULT NULL COMMENT '描述',
    proof_pics varchar(1000) DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
    handle_note varchar(500) DEFAULT NULL COMMENT '处理备注',
    receive_time datetime DEFAULT NULL COMMENT '收货时间',
    receive_note varchar(500) DEFAULT NULL COMMENT '收货备注',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '退单';

CREATE TABLE vvshop_goods.spu_full_reduction
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    spu_id              bigint(20) NOT NULL COMMENT 'SPUID',
    full_price          decimal(10,2) DEFAULT NULL,
    reduce_price        decimal(10,2) DEFAULT NULL,
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment 'spu满减表';

DROP TABLE vvshop_goods.home_advertise;
CREATE TABLE vvshop_goods.home_advertise
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    theme              varchar(256)  NULL COMMENT '主题',
    type                int(1) DEFAULT NULL COMMENT '位置：0->轮播图，1->首页活动位',
    img_url            varchar(256) NOT NULL COMMENT '图片链接',
    forward_url        varchar(256) NOT NULL COMMENT '跳转链接',
    status             tinyint   default 0  NULL COMMENT '上线状态 0-未上线 1-已上线',
    sort_id            bigint(20) NOT NULL COMMENT '排序ID',
    note               varchar(500) DEFAULT NULL COMMENT '备注',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '轮播图表';

DROP TABLE vvshop_goods.groupon_activity;
CREATE TABLE vvshop_goods.groupon_activity
(
    id              bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    name            varchar(256) NOT NULL COMMENT '拼团活动名称',
    activity_no     varchar(256) NOT NULL COMMENT '团购活动编号',
    spu_id          bigint(20) NOT NULL COMMENT '商品id',
    cover_url       varchar(520) NOT NULL COMMENT '封面url',
    status          tinyint(2) NOT NULL COMMENT '活动状态：0:下线 1：上线',
    introduction    text NOT NULL COMMENT '介绍（富文本）',
    start_time      timestamp NOT NULL  COMMENT '开始时间',
    end_time        timestamp NOT NULL  COMMENT '结束时间',
    original_price  decimal(20, 2) NOT NULL COMMENT '课程原价',
    group_price     decimal(20, 2) NOT NULL COMMENT '团购价格',
    unit            int(11) NOT NULL COMMENT '拼团人数',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购活动表';


DROP TABLE vvshop_goods.groupon;
CREATE TABLE vvshop_goods.groupon
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    creator_id          int(11) NOT NULL COMMENT '创建人用户Id',
    creator_name        varchar(256) NULL DEFAULT NULL COMMENT '创建人name',
    groupon_activity_id int(11) NOT NULL DEFAULT 0 COMMENT '拼团活动id',
    success_time        timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单完成时间',
    status              tinyint(2) NOT NULL COMMENT '拼团状态:0 拼团中 1成功  2失败',
    type                tinyint(2) NOT NULL COMMENT '成团类型 ：0 学员拼团成功 1机构手动成团',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购表';


DROP TABLE vvshop_goods.groupon_order;
CREATE TABLE vvshop_goods.groupon_order
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    customer_id         int(11)     NOT NULL  COMMENT '学生ID',
    order_id            bigint(20)  NOT NULL  COMMENT '订单ID',
    pay_order_id        bigint(20) NOT NULL  COMMENT '支付订单ID',
    trade_no            bigint(20) NOT NULL  COMMENT '交易流水号',
    groupon_activity_id int(11) NOT NULL COMMENT '拼团活动id',
    groupon_id          int(11) NOT NULL COMMENT '团id',
    total_amount        decimal(20, 2) NOT NULL COMMENT '总金额',
    discount_amount    decimal(20, 2) NOT NULL COMMENT '优惠金额',
    status              tinyint(2) NOT NULL  COMMENT '订单状态:0：待付款，1 取消 2 待成团  3 拼团成功 4退款中5 拼团失败',
    operate_type        tinyint(2) NOT NULL  COMMENT '操作类型 ：0 手动成团 1 手动退款',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    success_time        timestamp DEFAULT NULL NULL COMMENT '订单完成时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购订单表';


DROP TABLE vvshop_goods.groupon_order_sku;
CREATE TABLE vvshop_goods.groupon_order_sku
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    spu_id              int(11)         NOT NULL  COMMENT '商品ID',
    spu_name            varchar(256)     NOT NULL  COMMENT '商品name',
    sku_id              int(11)     NOT NULL  COMMENT '商品skuID',
    sku_name            varchar(256)     NOT NULL  COMMENT 'skuName',
    groupon_order_id    bigint(20) DEFAULT NULL COMMENT '订单id',
    groupon_order_no    varchar(256) NOT NULL COMMENT '订单编号',
    sku_spec            varchar(1024) DEFAULT NULL COMMENT 'sku属性JSON',
    brand_id            bigint(20) NOT NULL COMMENT '品牌ID',
    brand_name          varchar(256) NOT NULL COMMENT '品牌名称',
    shop_id             bigint(20) NOT NULL COMMENT '店铺ID',
    shop_name           varchar(256) NOT NULL COMMENT '店铺名称',
    quantity            int(11) NOT NULL COMMENT '购买数量',
    price               bigint(20) NOT NULL COMMENT '价格',
    item_id             bigint(20) NOT NULL COMMENT '类别ID',
    discount_amount    decimal(10,2) DEFAULT NULL COMMENT '商品促销分解金额',
    real_amount         decimal(10,2) DEFAULT NULL COMMENT '该商品经过优惠后的分解金额',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购订单表';


CREATE TABLE vvshop_goods.shop_wechat_info (
     id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
     shop_id             bigint(11) NOT NULL  COMMENT '店铺id',
     open_id            varchar(256) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收款微信openId',
     real_name          varchar(256) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收款微信持有人真实姓名',
     nick_name          varchar(256) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收款微信昵称',
     sex                tinyint(1)  DEFAULT 0 COMMENT '收款微信性别，值为1时是男性，值为2时是女性，值为0时是未知',
     head_img_url       varchar(400) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头\\n  像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
     del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
     update_time         timestamp default NULL NULL COMMENT '更新时间',
     create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
     PRIMARY KEY (id),
     KEY idx_shop_id (shop_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购商铺收款微信信息';


CREATE TABLE vvshop_goods.wx_pay_order (
     id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
     order_status        tinyint(4) unsigned DEFAULT 0 COMMENT '交易状态',
     pay_order_no       bigint(20) unsigned NOT NULL COMMENT '支付订单号',
     trade_order_no     bigint(20) DEFAULT NULL COMMENT '有效交易订单号',
     pay_money          bigint(20) NOT NULL DEFAULT '0' COMMENT '订单实付金额（分）',
     callback_url       varchar(1000) DEFAULT NULL COMMENT '支付成功通知业务库接口',
     notify_url         varchar(1000) DEFAULT NULL COMMENT '支付成功通知支付库接口',
     goods_body         varchar(512) NOT NULL COMMENT '商品描述',
     goods_detail       varchar(512) NOT NULL COMMENT '商品详情',
     trade_succ_time    timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '支付时间',
     update_time        timestamp default NULL NULL COMMENT '更新时间',
     create_time        timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
     shop_id        bigint(20) unsigned NOT NULL COMMENT '支付userid',
     PRIMARY KEY (id),
     unique key(pay_order_no),
     KEY idx_trade_order_no (trade_order_no)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购支付订单';

CREATE TABLE vvshop_goods.wx_trade_order (
   id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
   trade_order_no       bigint(20) NOT NULL  COMMENT '交易订单号',
   pay_order_no         bigint(20) unsigned NOT NULL COMMENT '支付订单号',
   order_status         tinyint(4) unsigned DEFAULT 0 COMMENT '交易状态',
   transaction_id       varchar(64) NOT NULL DEFAULT '' COMMENT '微信支付订单号',
   pay_money            bigint(20) NOT NULL DEFAULT '0' COMMENT '订单实付金额（分）',
   terminal_ip          varchar(32) NOT NULL DEFAULT '' COMMENT '支付终端IP',
   response_info        varchar(1024) NOT NULL DEFAULT '' COMMENT '返回信息',
   trade_succ_time      timestamp NULL DEFAULT NULL COMMENT '支付时间',
   update_time          timestamp default NULL NULL COMMENT '更新时间',
   create_time          timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
   shop_id              bigint(20) unsigned NOT NULL COMMENT '支付userid',
   PRIMARY KEY (id),
   unique key(trade_order_no),
   KEY idx_trade_order_no (trade_order_no)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购交易订单';


CREATE TABLE vvshop_goods.wx_refund_order (
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    refund_status tinyint(4) DEFAULT '0' COMMENT '退款状态 ',
    refund_order_no bigint(20) NOT NULL DEFAULT '0' COMMENT '支付库退款订单编号',
    pay_order_no bigint(20) DEFAULT '0' COMMENT '支付订单编号',
    trade_order_no bigint(20) DEFAULT '0' COMMENT '交易订单编号',
    refund_reason varchar(250) NOT NULL DEFAULT '' COMMENT '退款原因',
    refund_money decimal(10,2) COMMENT '订单退款金额，分',
    pay_money bigint(20) NOT NULL DEFAULT '0' COMMENT '订单实付金额（分）',
    callback_url varchar(1000) DEFAULT '' COMMENT '退款结果通知业务库接口',
    notify_url varchar(1000) DEFAULT '' COMMENT '退款状态变更通知地址',
    refund_recv_accout varchar(1000) DEFAULT '' COMMENT '退款入账账户',
    refund_channel varchar(1000) DEFAULT '' COMMENT 'ORIGINAL—原路退款 BALANCE—退回到余额',
    third_refund_id varchar(128) DEFAULT '0' COMMENT '三方退款订单号',
    finish_time timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '退款处理完成时间',
    create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    shop_id bigint(20) unsigned NOT NULL COMMENT '支付userid',
    PRIMARY KEY (id),
    unique key(refund_order_no),
    unique key(trade_order_no)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '退款订单表';


CREATE TABLE vvshop_goods.wx_payment_record (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  trade_money decimal(10,2) NOT NULL DEFAULT '0' COMMENT '交易金额，单位为分',
  fee_money decimal(10,2) NOT NULL DEFAULT '0' COMMENT '手续费，单位为分',
  settle_money decimal(10,2) NOT NULL DEFAULT '0' COMMENT '结算金额，单位为分',
  payment_status int(11) NOT NULL DEFAULT '0' COMMENT '打款状态',
  receiver varchar(128) NOT NULL DEFAULT '' COMMENT '收款人',
  name varchar(32) NOT NULL DEFAULT '' COMMENT '收款人姓名',
  payment_no bigint(20) NOT NULL DEFAULT '0' COMMENT '打款流水号',
  wx_payment_no varchar(128) DEFAULT NULL COMMENT '微信打款流水号',
  reject_reason varchar(1024) NOT NULL DEFAULT '' COMMENT '终止打款原因',
  pay_success_time timestamp NOT NULL COMMENT '打款成功日期',
  create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  shop_id bigint(20) unsigned NOT NULL COMMENT '支付userid',
  PRIMARY KEY (id),
  unique key(payment_no)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购微信打款记录';



CREATE TABLE vvshop_goods.wx_settle_record (
     id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
     payment_type tinyint(4) DEFAULT 0 COMMENT '打款方式 0：企业付款到微信 1：标记打款',
     settle_status varchar(128) NOT NULL DEFAULT '' COMMENT '付款状态',
     payment_no bigint(20) NOT NULL DEFAULT 0 COMMENT '打款流水号',
     order_money int(11) NOT NULL DEFAULT 0 COMMENT '原订单金额，单位为分',
     transaction_id varchar(64) NOT NULL DEFAULT '' COMMENT '微信支付订单号',
     settle_money int(11) NOT NULL DEFAULT 0 COMMENT '结算金额，单位为分',
     fee_money int(11) NOT NULL DEFAULT 0 COMMENT '手续费，单位为分',
     trade_no varchar(128) NOT NULL DEFAULT '' COMMENT '交易单号',
     trade_time timestamp NOT NULL COMMENT '交易成功日期',
     create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     shop_id bigint(20) unsigned NOT NULL COMMENT '支付userid',
     PRIMARY KEY (id),
     unique key(trade_no),
     KEY idx_payment_no (payment_no)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '团购微信结算记录';

CREATE TABLE vvshop_goods.wx_core_log (
    id bigint(21) NOT NULL AUTO_INCREMENT COMMENT 'id',
    info_level varchar(32) DEFAULT NULL COMMENT '级别',
    info_keyword varchar(128) DEFAULT NULL COMMENT '关键字',
    search_keyword varchar(128) DEFAULT NULL COMMENT '搜索关键字',
    info_body varchar(1024) DEFAULT NULL COMMENT '参数',
    order_no bigint(21) DEFAULT NULL COMMENT '订单号',
    cur_host varchar(128) DEFAULT NULL COMMENT '主机',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
    pay_exception varchar(5280) NULL COMMENT '支付错误',
    alarm_msg varchar(255) NULL COMMENT '报警日志',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '微信日志';