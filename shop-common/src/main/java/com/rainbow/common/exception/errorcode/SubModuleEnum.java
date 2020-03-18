package com.rainbow.common.exception.errorcode;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 系统子系统划分
 *
 * @author lujinwei
 * @since 2018-11-09
 */
@Getter
public enum SubModuleEnum implements IEnum<Integer> {
    BASE(1, "基础"),
    ADMIN(2, "管理模块"),
    PORTAL(3, "门户模块"),
    ;

    private Integer value;
    private String desc;


    SubModuleEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
