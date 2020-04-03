package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnOrderStatusEnum implements IEnum<Integer> {

    WAIT_TO_HANDLE(0, "待处理"),
    RETURNING(1, "退货中"),
    FINISHED(2, "已完成"),
    REFUSED(3, "已拒绝"),;

    private Integer value;
    private String desc;
}
