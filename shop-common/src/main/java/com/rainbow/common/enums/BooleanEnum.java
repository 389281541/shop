package com.rainbow.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除枚举类
 * @author lujinwei
 * @date 2019-01-07
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum implements IEnum<Integer> {

    NO(0, "FALSE"),
    YES(1, "TRUE"),
    ;

    private Integer value;
    private String desc;

}
