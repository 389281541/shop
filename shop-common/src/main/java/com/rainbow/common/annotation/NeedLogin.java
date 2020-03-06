package com.rainbow.common.annotation;

import java.lang.annotation.*;

/**
 * 接口是否需要登录注解
 *
 * @author lujinwei
 * @Date 2019-02-26 20:17
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
    NeedLoginLevelEnum value() default NeedLoginLevelEnum.YES;
}
