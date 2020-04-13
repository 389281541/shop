package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayTypeEnum implements IEnum<Integer> {

    ALI_PAY(0,"支付宝"),
    WX_PAY(1, "微信支付");

    private Integer value;
    private String desc;
}
