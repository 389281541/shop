package com.rainbow.admin.enums;

/**
 * 验证码状态枚举
 *
 * @author lujinwei
 * @since 2019-06-26
 */
public enum KaptchaStatusEnum {

    SUCESS(0, "验证成功"),
    FAIL(1, "验证失败"),
    EXPIRED(2, "验证码过期");

    private Integer value;
    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    KaptchaStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}

