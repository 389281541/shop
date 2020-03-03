package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScopeTypeEnum implements IEnum<Integer> {

    ALL(0, "全场通用"),
    DESIGNATED_ITEM (1, "指定分类"),
    DESIGNATED_GOODS(2, "指定商品");

    private Integer value;
    private String desc;
}
