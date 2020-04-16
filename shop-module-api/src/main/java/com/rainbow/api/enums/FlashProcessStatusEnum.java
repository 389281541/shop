package com.rainbow.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 秒杀进程状态
 *
 * @author lujinwei
 * @since 2020/4/14
 */
@Getter
@AllArgsConstructor
public enum FlashProcessStatusEnum  implements IEnum<Integer> {

    WAIT_TO_FLASH(0, "待秒杀"),
    FLASHING(1, "秒杀中"),;

    private Integer value;
    private String desc;
}
