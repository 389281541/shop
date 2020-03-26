package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTypeEnum implements IEnum<Integer> {

    COMMON(0, "正常订单"),
    FLASH(1, "秒杀订单");

    private Integer value;
    private String desc;
}
