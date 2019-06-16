package com.rainbow.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * 删除枚举类
 * @author lujinwei
 * @date 2019-01-07
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DelFlagEnum implements IEnum<Integer> {

    NO(0, "未删除"),
    YES(1, "已删除"),
    ;

    private Integer value;
    private String desc;

    DelFlagEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }
}
