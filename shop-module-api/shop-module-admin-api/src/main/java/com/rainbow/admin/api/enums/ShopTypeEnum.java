package com.rainbow.admin.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShopTypeEnum implements IEnum<Integer> {

    OWN(1, "自营"),
    PLATFORM(2, "平台");

    private Integer value;
    private String desc;
}
