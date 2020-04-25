package com.rainbow.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * redis key集合类
 * 维护所有redis key
 * @author lujinwei
 * @since  2029/1/18
 */
public interface RedisKeyEnums {

    String SYSTEM_PREFIX = "rb";
    String SEPARATOR = ":";

    /**
     * 子系统
     *
     * @return
     */
    String getSubSystem();

    /**
     * 业务key
     *
     * @return
     */
    String getBusinessKey();

    /**
     * 获取缓存key
     *
     * @return
     */
    default String getRedisKey() {
        return SYSTEM_PREFIX + SEPARATOR + this.getSubSystem() + SEPARATOR + this.getBusinessKey();
    }


    @Getter
    @AllArgsConstructor
    enum ADMIN implements RedisKeyEnums {

        /**
         *
         */
        CRM_PREFIX("", "key前缀"),;

        private String businessKey;

        private String desc;

        @Override
        public String getSubSystem() {
            return SubSystemEnum.admin.getValue();
        }

        @Override
        public String toString() {
            return getRedisKey();
        }
    }

    @Getter
    @AllArgsConstructor
    enum PORTAL implements RedisKeyEnums {

        REDIS_KEY_PREFIX_ORDER_ID("redis_key_prefix_order_id", "报名人次"),
        REDIS_KEY_PREFIX_FLASH_GOODS_KEY("redis_key_prefix_flash_goods_key", "秒杀"),
        REDIS_KEY_PREFIX_STOCK_KEY("redis_key_prefix_stock_key", "秒杀商品库存"),
        REDIS_KEY_PREFIX_FLASH_ORDER_KEY("redis_key_prefix_flash_order_key", "秒杀订单");
        private String businessKey;

        private String desc;

        @Override
        public String getSubSystem() {
            return SubSystemEnum.portal.getValue();
        }

        @Override
        public String toString() {
            return getRedisKey();
        }
    }

    @Getter
    enum SubSystemEnum {
        common("common"),
        admin("admin"),
        portal("portal"),;

        private String value;

        SubSystemEnum(String value) {
            this.value = value;
        }
    }
}
