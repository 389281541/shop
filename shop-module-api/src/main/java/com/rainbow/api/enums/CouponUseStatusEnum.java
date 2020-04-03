package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券使用状态枚举
 *
 * @author lujinwei
 * @since 2020/3/19
 */
@Getter
@AllArgsConstructor
public enum CouponUseStatusEnum implements IEnum<Integer> {
//    使用状态：0->未使用；1->已使用；2->已过期
    NON_USE(0, "未使用"),
    USED(1, "已使用"),
    EXPIRED(2, "已过期");

    private Integer value;
    private String desc;
}
