package com.rainbow.common.annotation;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Nologin校验级别
 *
 * @author huangjian
 * @Date 2019-03-25 10:09
 */
@Getter
@AllArgsConstructor
public enum NeedLoginLevelEnum implements IEnum<Integer> {
    /**
     * 不需要登录，但是需要有一些特定信息
     */
    WEAK(0, "弱登录"),
    /**
     * 不需要登录，也不需要机构信息
     */
    YES(1, "需要登录");
    private Integer value;
    private String desc;
}
