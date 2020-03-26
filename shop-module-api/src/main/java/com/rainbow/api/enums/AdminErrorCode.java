package com.rainbow.api.enums;


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
    UNKNOW(200001, "未知"),
    SPU_PULL_OFF(200002, "spu下架了"),
    USER_NOT_EXIST(200003, "用户不存在"),
    USER_ALREADY_EXIST(200004, "用户已经存在"),
    PASSWORD_ERROR(200005, "密码错误"),
    TOKEN_ERROR(200006,"TOKEN错误"),
    TOKEN_FORMAT_ERROR(200007,"授权码格式不正确")
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
        this(ErrorSideEnum.SERVER, SubModuleEnum.ADMIN, code, message);
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
