package com.rainbow.common.exception.errorcode;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 异常来源方
 *
 * @author lujinwei
 * @since 2018-11-09
 */
@Getter
public enum ErrorSideEnum implements IEnum<Integer> {

    CLIENT(1, "发生在客户端，并且是客户端使用的code,服务端不要用哦"),
    SERVER(2, "服务端"),
    ;

    private Integer value;
    private String desc;

    ErrorSideEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
