package com.rainbow.api.enums;


import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HomeAdvertiseTypeEnum implements IEnum<Integer> {

    ROTATE_CHART(0, "轮播图"),
    ACTIVITY(1, "活动");

    private Integer value;
    private String desc;
}
