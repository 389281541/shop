package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码状态枚举
 *
 * @author lujinwei
 * @since 2019-06-26
 */
@Getter
@AllArgsConstructor
public enum KaptchaStatusEnum implements IEnum<Integer> {

    SUCESS(0, "验证成功"),
    FAIL(1, "验证失败"),
    EXPIRED(2, "验证码过期");

    private Integer value;
    private String desc;
}

