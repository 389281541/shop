package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 优惠券范围类型枚举
 *
 * @author lujinwei
 * @since 2020/3/19
 */
@Getter
@AllArgsConstructor
public enum CouponScopeTypeEnum implements IEnum<Integer> {
    ALL_SCENE(0, "全场景"),
    SPECIFIED_ITEM(1, "指定分类"),
    SPECIFIED_SPU(2, "指定商品");

    private Integer value;
    private String desc;
}
