package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 促销类型枚举
 *
 * @author lujinwei
 * @since 2020/3/15
 */
@Getter
@AllArgsConstructor
public enum PromotionTypeEnum implements IEnum<Integer> {

    NO_PROMOTION(0, "无促销"),
    TIME_LIMIT(1, "限时促销"),
    FULL_REDUCTION(2, "满减优惠");

    private Integer value;
    private String desc;
}
