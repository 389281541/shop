package com.rainbow.admin.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements IEnum<Integer> {

    NON_PAY(0, "待付款"),
    NON_DELIVER(1, "待发货"),
    DELIVERED(2, "已发货"),
    FINISHED(3, "已完成"),
    CLOSED(4, "已关闭"),
    INVALID(5, "无效订单");

    private Integer value;
    private String desc;
}
