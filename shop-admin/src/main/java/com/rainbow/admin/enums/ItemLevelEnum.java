package com.rainbow.admin.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * 枚举
 *
 * @author lujinwei
 * @since 2019-06-26
 */
@Getter
@AllArgsConstructor
public enum ItemLevelEnum implements IEnum<Boolean> {
    PARENT(TRUE, "一级"),
    NON_PARENT(FALSE, "二级");

    private Boolean value;
    private String desc;
}

