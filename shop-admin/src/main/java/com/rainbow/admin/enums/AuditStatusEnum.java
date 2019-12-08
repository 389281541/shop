package com.rainbow.admin.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditStatusEnum implements IEnum<Integer> {

    REJECTED(0, "审核拒绝"),
    AUDITING(1, "正在审核"),
    PASS(2, "审核通过");

    private Integer value;
    private String desc;
}
