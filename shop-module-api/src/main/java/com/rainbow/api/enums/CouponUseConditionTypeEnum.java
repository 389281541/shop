package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券使用门槛类型枚举
 *
 * @author lujinwei
 * @since 2020/3/19
 */
@Getter
@AllArgsConstructor
public enum CouponUseConditionTypeEnum implements IEnum<Integer> {

    NO_THRESHOLD(0, "无门槛"),
    MINIMUM_AMOUNT(1, "满足多少金额");

    private Integer value;
    private String desc;
}
