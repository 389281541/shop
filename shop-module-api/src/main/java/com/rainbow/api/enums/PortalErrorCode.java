package com.rainbow.api.enums;

import com.rainbow.common.exception.errorcode.ErrorSideEnum;
import com.rainbow.common.exception.errorcode.SubModuleEnum;
import com.rainbow.common.exception.errorcode.UniverseErrorCode;

/**
 * 门户入口错误码
 *
 * @author lujinwei
 * @since 2020/3/16
 */
public enum PortalErrorCode implements UniverseErrorCode {

    UNKNOW(1, "未知"),
    SPU_PULL_OFF(2, "spu下架了"),
    PROMOTION_LIMIT_EXCEED(3, "超出限购数了"),
    ;

    private ErrorSideEnum errorSideEnum;
    private SubModuleEnum subModuleEnum;
    private int code;
    private String message;

    PortalErrorCode(ErrorSideEnum errorSideEnum, SubModuleEnum subModuleEnum, int code, String msg) {
        this.errorSideEnum = errorSideEnum;
        this.subModuleEnum = subModuleEnum;
        this.code = code;
        this.message = msg;
    }

    PortalErrorCode(int code, String message) {
        this(ErrorSideEnum.SERVER, SubModuleEnum.PORTAL, code, message);
    }

    @Override
    public ErrorSideEnum getErrorSideEnum() {
        return errorSideEnum;
    }

    @Override
    public SubModuleEnum getSubSystem() {
        return subModuleEnum;
    }

    @Override
    public SubModuleEnum getSubModule() {
        return subModuleEnum;
    }

    @Override
    public int getDetailCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public UniverseErrorCode fromCode(int code) {
        for (PortalErrorCode errorCode : values()) {
            if (errorCode.getDetailCode() == code) {
                return errorCode;
            }
        }
        return UNKNOW;
    }
}
