package com.rainbow.common.exception;

import com.rainbow.common.exception.errorcode.UniverseErrorCode;
import lombok.Getter;

/**
 * 业务逻辑异常
 * @author lujinwei
 * @date 2019/1/8 上午10:06
 */
@Getter
public class BusinessException extends CustomException {

    private static final long serialVersionUID = -458678964789349979L;

    public BusinessException(UniverseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(UniverseErrorCode errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
    }

}