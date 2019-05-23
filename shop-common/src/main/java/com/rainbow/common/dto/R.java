package com.rainbow.common.dto;

import com.rainbow.common.exception.errorcode.UniverseErrorCode;
import lombok.Data;

/**
 * 响应信息主体
 *
 * @author lujinwei
 * @date 2018/9/27 下午5:15
 */
@Data
public class R<T> extends BaseDTO {

    /**
     * 正常返回代码 = 0
     */
    private int code = 0;
    private String message = "";
    private String stackMessage = "";
    private T data;

    public R() {
        super();
    }

    /**
     * 正常返回值
     *
     * @param data
     */
    public R(T data) {
        super();
        this.data = data;
    }

    /**
     * 返回错误代码
     */
    public R(UniverseErrorCode universeErrorCode) {
        super();
        this.code = code(universeErrorCode);
        this.message = universeErrorCode.getMessage();
    }

    /**
     * 返回错误代码 + 异常信息
     *
     * @param universeErrorCode
     * @param stackMessage
     */
    public R(UniverseErrorCode universeErrorCode, String stackMessage) {
        super();
        this.code = code(universeErrorCode);
        this.message = universeErrorCode.getMessage();
        this.stackMessage = stackMessage;
    }

    /**
     * 返回错误代码 + 异常信息
     *
     * @param universeErrorCode
     * @param message
     * @param stackMessage
     */
    public R(UniverseErrorCode universeErrorCode, String message, String stackMessage) {
        super();
        this.code = code(universeErrorCode);
        this.message = message;
        this.stackMessage = stackMessage;
    }


    private int code(UniverseErrorCode universeErrorCode) {
        return universeErrorCode.getErrorSideEnum().getValue() * 100000 + universeErrorCode.getSubModule().getValue() * 1000 + universeErrorCode.getDetailCode();
    }

}
