package com.rainbow.common.exception;

import com.rainbow.common.exception.errorcode.UniverseErrorCode;
import lombok.Getter;

/**
 * 业务逻辑异常
 *
 * @author lujinwei
 * @since 2019-01-08
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