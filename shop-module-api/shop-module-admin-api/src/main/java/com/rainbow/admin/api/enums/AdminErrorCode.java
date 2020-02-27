package com.rainbow.admin.api.enums;


import com.rainbow.common.exception.errorcode.ErrorSideEnum;
import com.rainbow.common.exception.errorcode.SubModuleEnum;
import com.rainbow.common.exception.errorcode.UniverseErrorCode;

/**
 * 用户模块错误码
 *
 * @author lujinwei
 * @date 2019/12/4 下午6:56
 */
public enum AdminErrorCode implements UniverseErrorCode {
    UNKNOW(1, "未知"),
    SPU_PULL_OFF(2, "spu下架了"),
    ;


    private ErrorSideEnum errorSideEnum;
    private SubModuleEnum subModuleEnum;
    private int code;
    private String message;

    AdminErrorCode(ErrorSideEnum errorSideEnum, SubModuleEnum subModuleEnum, int code, String msg) {
        this.errorSideEnum = errorSideEnum;
        this.subModuleEnum = subModuleEnum;
        this.code = code;
        this.message = msg;
    }

    AdminErrorCode(int code, String message) {
        this(ErrorSideEnum.SERVER, SubModuleEnum.USER, code, message);
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

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public UniverseErrorCode fromCode(int code) {
        for (AdminErrorCode errorCode : values()) {
            if (errorCode.getDetailCode() == code) {
                return errorCode;
            }
        }
        return UNKNOW;
    }

}
