package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券获取类型枚举
 *
 * @author lujinwei
 * @since 2020/3/19
 */
@Getter
@AllArgsConstructor
public enum CouponReceiveTypeEnum implements IEnum<Integer> {

    SYSTEM_GIFT(0, "后台赠送"),
    SELF_COLLECT(1, "主动领取");

    private Integer value;
    private String desc;
}
