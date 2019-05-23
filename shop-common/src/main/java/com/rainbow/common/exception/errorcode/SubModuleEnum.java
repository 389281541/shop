package com.rainbow.common.exception.errorcode;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 系统子系统划分
 *
 * @author lujinwei
 * @Date 2018/11/9 16:10
 */
@Getter
public enum SubModuleEnum implements IEnum<Integer> {
    BASE(1, "基础"),
    USER(2, "用户模块"),
    OEDER(3, "订单模块"),
    MESSAGE(4, "消息模块"),
    COMMON(5, "公用模块"),
    TASK(6, "定时调度"),
    PAY(7, "支付模块"),
    ;

    private Integer value;
    private String desc;


    SubModuleEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
