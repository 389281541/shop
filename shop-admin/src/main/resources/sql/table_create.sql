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

INSERT INTO vvshop_user.administrator VALUES (1, 'admin', '55136CC33446A61D43FAB2A7F6B6F662', 'e8iN9Zpb', '15801248054', null, 0, '2019-06-20 20:05:52', '2019-06-20 20:05:46', '2019-06-20 20:05:53');
INSERT INTO vvshop_user.administrator VALUES (2, 'rainbow', '9275B6FA484E35F444705132ACD5B98F', 'ZaUoItV1', '15801248054', null, 0, '2019-06-21 15:32:39', '2019-06-21 15:32:35', '2019-06-21 15:32:40');


CREATE TABLE vvshop_user.customer
(
    id                  bigint(20)   AUTO_INCREMENT         NOT NULL COMMENT '主键ID',
    user_name           varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户名',
    mobile              varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户电话',
    avatar              varchar(512) COLLATE utf8mb4_bin    NULL COMMENT '用户头像',
    identity_name       varchar(512) COLLATE utf8mb4_bin    NULL COMMENT '真实姓名',
    identity_type       tinyint      NULL DEFAULT 1 COMMENT '证件类型：1 身份证，2 军官证，3 护照',
    identity_no         varchar(20)  NULL COMMENT '证件号码',
    email               varchar(50)  NULL COMMENT '邮箱',
    gender              tinyint(1)   NOT NULL COMMENT '性别: 0-男 1-女',
    birthday            timestamp    NULL COMMENT '会员生日',
    user_status     tinyint   default 0                 NOT NULL COMMENT '用户状态 0-正常 1-禁用',
    update_time     timestamp default NULL NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    last_login_time timestamp default NULL NULL COMMENT '上次登陆时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_user_name (user_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '顾客登录表';

CREATE TABLE vvshop_user.receiver (
    id                  bigint(20)   AUTO_INCREMENT         NOT NULL COMMENT '主键ID',
    customer_id         bigint(20)  NOT NULL COMMENT '用户ID',
    receiver_name       varchar(64) NOT NULL COMMENT '收货人姓名',
    zip                 varchar(64) NOT NULL COMMENT '邮编',
    province_code       smallint    NOT NULL COMMENT '地区表中省份的code',
    city_code           smallint    NOT NULL COMMENT '地区表中城市的code',
    district_code       smallint    NOT NULL COMMENT '地区表中的区code',
    address             varchar(200) NOT NULL COMMENT '具体的地址门牌号',
    is_default          tinyint default 0 NOT NULL COMMENT '是否默认 0-否 1-是',
    del_status      tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time     timestamp default NULL NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '顾客地址表';

CREATE TABLE vvshop_user.customer_account (
  id                  bigint(20)   AUTO_INCREMENT         NOT NULL COMMENT '主键ID',
  customer_id         bigint(20)  NOT NULL COMMENT '用户ID',
  points               int(11)     NOT NULL DEFAULT 0 COMMENT '积分',
  customer_level      tinyint NOT NULL DEFAULT 0 COMMENT '会员等级：0-非会员 1-普通会员，2-青铜，3-白银，4-黄金，5-钻石',
  is_vip              tinyint default 0 NOT NULL COMMENT '是否会员级别会员',
  del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
  update_time     timestamp default NULL NULL COMMENT '更新时间',
  create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  last_login_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '上次登陆时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '顾客账号信息';

CREATE TABLE vvshop_user.customer_level(
   id          bigint(20)   AUTO_INCREMENT   NOT NULL COMMENT '主键ID',
   level_name  varchar(10) NOT NULL COMMENT '会员级别名称',
   min_point INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '该级别最低积分',
   max_point INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '该级别最高积分',
   update_time     timestamp default NULL NULL COMMENT '更新时间',
   create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
   PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户会员级别信息表';

CREATE TABLE vvshop_goods.brand
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    name varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '品牌名称',
    logo  varchar(256) COLLATE utf8mb4_bin NULL COMMENT '品牌logo',
    description  varchar(1024) COLLATE utf8mb4_bin NULL COMMENT '品牌描述',
    sort_id bigint(20) NOT NULL COMMENT '排序ID',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    del_status tinyint default 0          NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_brand_name (name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '品牌表';

INSERT INTO vvshop_goods.brand VALUES (1, '卡尼尔', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'http://pw7pqrtwr.bkt.clouddn.com/4e56f8f814bb400fb613168901232acd','卡尼尔，英文名Garnier，全称AIfred Amour Garnier,创建于1903年，是一种法国的化妆品', 1, 11);


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

INSERT INTO vvshop_goods.item VALUES (1, '面部护肤', 1001, null, 1, 1, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (2, '洗发护发', 1002, null, 2, 1, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (3, '身体护理', 1003, null, 3, 1, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (4, '口腔护理', 1004, null, 4, 1, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (5, '女性护理', 1005, null, 5, 1, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (6, '香水彩妆', 1006, null, 6, 1, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (7, '清洁用品', 1007, null, 7, 1, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (8, '宠物生活', 1008, null, 8, 1, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (9, '补水保湿',  1009, 1, 1, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (10, '卸妆',    1010, 1, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (11, '洁面',    1011, 1, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (12, '爽肤水',   1012, 1, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (13, '乳液面霜', 1013, 1, 5, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (14, '精华',     1014, 1, 6, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (15, '眼霜',     1015, 1, 7, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (16, '防嗮',     1016, 1, 8, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (17, '面膜',     1017, 1, 9, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (18, '剃须',     1018, 1, 10, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (19, '套装',     1019, 1, 11, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (20, '洗发',    1020, 2, 1, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (21, '护发',    1021, 2, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (22, '染发',    1022, 2, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (23, '造型',    1023, 2, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (24, '假发',    1024, 2, 5, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (25, '美发工具', 1025, 2, 6, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (26, '套装',    1026, 2, 7, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (27, '补水保湿',    1027, 3, 1, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (28, '沐浴',        1028, 3, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (29, '润肤',        1029, 3, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (30, '精油',       1030, 3, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (31, '颈部',       1031, 3, 5, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (32, '手足',       1032, 3, 6, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (33, '纤体塑形',    1033, 3, 7, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (34, '美胸',       1034, 3, 8, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (35, '套装',       1035, 3, 9, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (36, '牙膏/牙粉',    1036, 4, 1, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (37, '牙刷/牙线',    1037, 4, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (38, '漱口水',       1038, 4, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (39, '套装',         1039, 4, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (40, '卫生巾',    1040, 5, 1, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (41, '卫生护垫',   1041, 5, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (42, '私密护理',   1042, 5, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (43, '脱毛膏',    1043, 5, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (69, 'BB霜',     1069, 6, 1, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (45, '女士香水',   1045, 6, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (46, '男士香水',   1046, 6, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (47, '底妆',      1047, 6, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (48, '眉笔',      1048, 6, 5, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (49, '睫毛膏',    1049, 6, 6, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (50, '眼线',     1050, 6, 7, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (51, '眼影',     1051, 6, 8, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (52, '唇膏/彩',   1052, 6, 9, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (44, '化妆棉',     1044, 6, 10, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (53, '纸品湿巾',   1053, 7, 1, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (54, '衣物清洁',   1054, 7, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (55, '清洁工具',   1055, 7, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (56, '家庭清洁',   1056, 7, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (57, '一次性用品',  1057, 7, 5, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (58, '驱虫用品',   1058, 7, 6, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (59, '皮具护理',   1059, 7, 7, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO vvshop_goods.item VALUES (60, '水族世界',   1060, 8, 1, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (61, '狗粮',      1061, 8, 2, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (62, '猫粮',      1062, 8, 3, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (63, '猫狗罐头',   1063, 8, 4, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (64, '狗零食',     1064, 8, 5, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (65, '猫零食',     1065, 8, 6, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (66, '医疗保健',   1066, 8, 7, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (67, '宠物玩具',   1067, 8, 8, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO vvshop_goods.item VALUES (68, '宠物服饰',   1068, 8, 9, 0, 0,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());



CREATE TABLE vvshop_goods.spec_name
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '属性名称',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    exist_alias tinyint(1) default 0 NOT NULL COMMENT '是否存在别名 0-不存在 1-存在',
    is_color tinyint(1) default 0 NOT NULL COMMENT '是否颜色属性 0-不是 1-是',
    is_enum tinyint(1) default 0 NOT NULL COMMENT '是否枚举属性 0-不是 1-是',
    is_input tinyint(1) default 0 NOT NULL COMMENT '是否输入属性 0-不是 1-是',
    is_key tinyint(1) default 0 NOT NULL COMMENT '是否关键属性 0-不是 1-是',
    is_sku tinyint(1) default 0 NOT NULL COMMENT '是否销售属性 0-不是 1-是',
    is_search tinyint(1) default 0 NOT NULL COMMENT '是否搜索字段 0-不是 1-是',
    is_must  tinyint(1) default 0 NOT NULL COMMENT '是否必须 0-不是 1-是',
    is_multiple  tinyint(1) default 0 NOT NULL COMMENT '是否多选 0-不是 1-是',
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


CREATE TABLE vvshop_goods.shop
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '店铺名称',
    type TINYINT NOT NULL COMMENT '店铺类型：1.自营，2.平台',
    phone_number VARCHAR(50) NOT NULL COMMENT '联系电话',
    bank_name VARCHAR(50) NOT NULL COMMENT '供应商开户银行名称',
    bank_account VARCHAR(50) NOT NULL COMMENT '银行账号',
    address VARCHAR(200) NOT NULL COMMENT '供应商地址',
    audit_status TINYINT NOT NULL DEFAULT 0 COMMENT '状态 0禁止，1启用',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY  (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺表';



CREATE TABLE vvshop_goods.spu
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
    spu_no bigint(20) NOT NULL COMMENT '商品编号',
    brand_id bigint(20) NOT NULL COMMENT '品牌ID',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    shop_id bigint(20) NOT NULL COMMENT '店铺ID',
    description varchar(1000) DEFAULT NULL COMMENT '商品描述',
    sale_status TINYINT NOT NULL DEFAULT 0 COMMENT '上下架状态 0下架 1上架',
    audit_status TINYINT NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核，1审核通过，2审核驳回',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品表';


CREATE TABLE vvshop_goods.spu_img
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    sku_id bigint(20) NOT NULL COMMENT '商品ID',
    img_url varchar(256) NOT NULL COMMENT '图片地址',
    is_cover tinyint NOT NULL DEFAULT 0 COMMENT '是否封面图 0-非封面图 1-封面图',
    is_master tinyint NOT NULL DEFAULT 0 COMMENT '是否主图 0-非主图 1-主图',
    sort_id int(11) NOT NULL COMMENT '图片位置',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default NULL NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品图片表';



CREATE TABLE vvshop_goods.spu_spec
(
    id            bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    spu_id    bigint(20)                          NOT NULL COMMENT '商品ID',
    spec_name_id  bigint(20)                          NOT NULL COMMENT '属性名ID',
    del_status    tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time   timestamp default NULL NULL COMMENT '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品基本属性表';


CREATE TABLE vvshop_goods.sku
(
    id            bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    sku_name      varchar(256) NOT NULL COMMENT 'SKU名称',
    sku_no       bigint(20) NOT NULL COMMENT '商品编码',
    spu_id        bigint(20) NOT NULL COMMENT '商品ID',
    shop_id       bigint(20) NOT NULL COMMENT '店铺ID',
    stock         int(11) NOT NULL COMMENT '库存',
    price         bigint(20) NOT NULL COMMENT '价格',
    del_status    tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time   timestamp default NULL NULL COMMENT '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品sku表';




CREATE TABLE vvshop_goods.sku_spec
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    sku_id              bigint(20) NOT NULL COMMENT 'SKUID',
    spec_value_id     varchar(128) NOT NULL COMMENT '属性值ID',
    sort_id             int(11) NOT NULL COMMENT '排序ID',
    del_status          tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment 'sku属性关联表';




CREATE TABLE vvshop_goods.cart
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    sku_id              bigint(20) NOT NULL COMMENT 'SKUID',
    spu_id              bigint(20) NOT NULL COMMENT 'SPUID',
    customer_id         bigint(20) NOT NULL COMMENT '用户ID',
    shop_id             bigint(20) NOT NULL COMMENT '店铺ID',
    amount              int(11) NOT NULL COMMENT '商品数量',
    price               bigint(20) NOT NULL COMMENT '价格',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '购物车表';


CREATE TABLE vvshop_goods.order
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    order_no            bigint(20) NOT NULL COMMENT '订单编号',
    item_count          int(11) NOT NULL COMMENT '商品项数',
    customer_id         bigint(20) NOT NULL COMMENT '用户ID',
    shop_id             bigint(20) NOT NULL COMMENT '店铺ID',
    flow_id             bigint(20) NOT NULL COMMENT '物流ID',
    payment_mode        tinyint   default NULL NULL COMMENT '支付方式 0：支付宝，1：微信，2：银行卡',
    payment_time        timestamp default NULL NULL COMMENT '支付时间',
    status              tinyint   default 0 NOT NULL COMMENT '订单状态',
    trade_no            bigint(20) default NULL NULL COMMENT '支付交易号',
    original_amount     bigint(20) NOT NULL COMMENT '原价总额',
    discount_amount     bigint(20) NOT NULL COMMENT '折扣金额',
    points_amount       bigint(20) NOT NULL COMMENT '积分金额',
    coupon_amount       bigint(20) NOT NULL COMMENT '优惠券金额',
    total_amount        bigint(20) NOT NULL COMMENT '支付金额',
    deliver_mode        tinyint default 0 NULL COMMENT '配送方式：0-快递 1-自取',
    deliver_fee         bigint(20) NOT NULL COMMENT '运费',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '订单表';


CREATE TABLE vvshop_goods.order_sku
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    order_no            bigint(20) NOT NULL COMMENT '订单编号',
    sku_id              bigint(20) NOT NULL COMMENT 'SKUID',
    spu_id              bigint(20) NOT NULL COMMENT 'SPUID',
    shop_id             bigint(20) NOT NULL COMMENT '店铺ID',
    amount              int(11) NOT NULL COMMENT '商品数量',
    price               bigint(20) NOT NULL COMMENT '价格',
    del_status          tinyint   default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '订单-sku关联表';




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
    order_no            bigint(20) NOT NULL COMMENT '订单编号',
    payment_mode        tinyint   default NULL NULL COMMENT '支付方式 0：支付宝，1：微信，2：银行卡',
    param               varchar(256)  COMMENT '支付参数json',
    remark              varchar(256)  NOT NULL COMMENT '备注信息',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间'
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '支付记录表';


CREATE TABLE vvshop_goods.order_return
(
    id                  bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    order_no            bigint(20) NOT NULL COMMENT '订单编号',
    customer_id         bigint(20) NOT NULL COMMENT '用户ID',
    user_name           varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户名',
    thumbs_up           bigint(20) NOT NULL COMMENT '点赞数',
    content             varchar(2048)  NOT NULL COMMENT '备注信息',
    score               smallint  NOT NULL COMMENT '评分',
    update_time         timestamp default NULL NULL COMMENT '更新时间',
    create_time         timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '支付记录表';







