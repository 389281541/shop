package com.rainbow.common.exception;

import com.rainbow.common.exception.errorcode.UniverseErrorCode;

/**
 * 自定义异常类
 *
 * @author lujinwei
 * @Date 2018/11/9 16:59
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -5653207638917828094L;

    protected String message;
    protected UniverseErrorCode errorCode;

    public CustomException() {
    }

    public int getErrorCode() {
        return errorCode.getErrorSideEnum().getValue() * 1000000
                + errorCode.getSubSystem().getValue() * 10000 +
                +errorCode.getSubModule().getValue() * 100
                + errorCode.getDetailCode();
    }

    @Override
    public final String getMessage() {
        if (this.message == null) {
            return errorCode.getMessage();
        }
        return this.message;
    }
}