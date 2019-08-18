CREATE TABLE vvshop_user.user
(
    id              bigint(20)   AUTO_INCREMENT         NOT NULL COMMENT '主键ID',
    user_name       varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户名',
    password        varchar(256) COLLATE utf8mb4_bin    NOT NULL COMMENT '密码',
    salt            varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '盐',
    mobile          varchar(100) COLLATE utf8mb4_bin    NOT NULL COMMENT '用户电话',
    avatar          varchar(512) COLLATE utf8mb4_bin    NULL COMMENT '用户头像',
    del_status      tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time     timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time     timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    last_login_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '上次登陆时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_user_name (user_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '用户表';


CREATE TABLE vvshop_goods.brand
(
    id  bigint(20)  AUTO_INCREMENT  COMMENT '主键ID',
    name varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '品牌名称',
    del_status tinyint default 0          NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    logo  varchar(256) COLLATE utf8mb4_bin NULL COMMENT '品牌logo',
    description  varchar(1024) COLLATE utf8mb4_bin NULL COMMENT '品牌描述',
    url varchar(256) COLLATE utf8mb4_bin NULL COMMENT '官方地址',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    PRIMARY KEY (id),
    UNIQUE KEY unique_brand_name (name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '品牌表';


CREATE TABLE vvshop_goods.product
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(256) COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
    brand_id bigint(20) COLLATE utf8mb4_bin NULL COMMENT '品牌',
    item_id bigint(20) NOT NULL COMMENT '类别ID',
    description varchar(1000) DEFAULT NULL COMMENT '商品描述',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品表';



CREATE TABLE vvshop_goods.item
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '类别名称',
    parent_id bigint(20) NULL COMMENT '父类别ID',
    sort_id bigint(20) NOT NULL COMMENT '排序ID',
    is_parent tinyint(1) NOT NULL comment '是否父级别 0-非父级别 1-父级别',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY unique_name (name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '类别表';



CREATE TABLE vvshop_goods.attribute_name
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
    update_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '属性名称表';


CREATE TABLE vvshop_goods.attribute_value
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    name varchar(128) NOT NULL COMMENT '属性值名称',
    attribute_id bigint(20) NOT NULL COMMENT '属性名ID',
    sort_id int(11) NOT NULL COMMENT '排序ID',
    is_multiple  tinyint(1) default 0 NOT NULL COMMENT '是否多选 0-不是 1-是',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '属性值表';


CREATE TABLE vvshop_goods.product_img
(
    id bigint(20)  AUTO_INCREMENT COMMENT '主键ID',
    product_id bigint(20) NOT NULL COMMENT '商品ID',
    img_url varchar(256) NOT NULL COMMENT '图片地址',
    img_position int(11) NOT NULL COMMENT '图片位置',
    del_status tinyint default 0 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品图片表';



CREATE TABLE vvshop_goods.product_attribute
(
    id            bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    product_id    bigint(20)                          NOT NULL COMMENT '商品ID',
    attr_name_id  bigint(20)                          NOT NULL COMMENT '属性名ID',
    attr_value_id bigint(20)                          NOT NULL COMMENT '属性值ID',
    is_sku        tinyint(1)                          NOT NULL COMMENT '是否SKU',
    sku_id        bigint(20)                          NOT NULL COMMENT 'SKUID',
    del_status    tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time   timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品基本属性表';


CREATE TABLE vvshop_goods.product_sku
(
    id            bigint(20) AUTO_INCREMENT COMMENT '主键ID',
    product_id    bigint(20) NOT NULL COMMENT '商品ID',
    amount        int(11)    NOT NULL COMMENT '数量',
    price         BIGINT(20) NOT NULL COMMENT '价格',
    sku_name      varchar(256) NOT NULL COMMENT 'SKU名称',
    attr_str      varchar(256) NOT NULL COMMENT '属性字符串',
    del_status    tinyint   default 0                 NOT NULL COMMENT '删除状态 0-未删除 1-已删除',
    update_time   timestamp default CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    create_time   timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '商品sku表';







