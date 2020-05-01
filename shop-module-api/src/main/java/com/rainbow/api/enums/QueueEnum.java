package com.rainbow.api.enums;

import lombok.Getter;

/**
 * rabbit mq消息队列配置
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("rainbow.order.direct", "rainbow.order.cancel", "rainbow.order.cancel"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("rainbow.order.direct.ttl", "rainbow.order.cancel.ttl", "rainbow.order.cancel.ttl"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_FLASH("rainbow.flash", "rainbow.flash", "rainbow.flash"),
    /**
     * topic队列1
     */
    QUEUE_TOPIC_1("rainbow.topic.exchange","rainbow.topic.queue1","rainbow.topic.key1"),
    /**
     * topic队列2
     */
    QUEUE_TOPIC_2("rainbow.topic.exchange","rainbow.topic.queue2","rainbow.topic.key2"),
    /**
     * FANOUT模式队列
     */
    QUEUE_FANOUT("rainbow.fanout.exchange","rainbow.fanout.queue","rainbow.fanout.key"),
    /**
     * HEADER模式队列
     */
    QUEUE_HEADER("rainbow.header.exchange","rainbow.header.queue","rainbow.header.key");
    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
