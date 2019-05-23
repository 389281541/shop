package com.rainbow.common.exception.errorcode;

/**
 * 公共异常处理
 * @author lujinwei
 * @date 2019/1/8 上午10:11
 */
public enum BaseErrorCode implements UniverseErrorCode {

    UNKNOW(1, "未知类型错误"),
    SERVICE_UNAVAILABLE(2, "服务暂停，请稍后再试"),
    API_NOT_FOUND(3, "请求的接口不存在"),
    IP_LIMIT(4, "IP限制不能请求该资源"),
    IP_REQUEST_OVER_LIMIT(5, "IP请求频次超过上限"),
    USER_REQUEST_OVER_LIMIT(6, "用户请求频次超过上限"),
    PERMISSION_DENIED(7, "没有权限进行此操作"),
    UN_SUPPORTED_FILE_TYPE(8, "不支持该文件类型"),
    FILE_UPLOAD_OSS_ERROR(9, "文件上传失败"),

    SUCCESS(200, "请求成功"),
    PARAM_ERROR(400, "请求参数不合法"),
    REQUIRE_PARAM(401, "缺少必要的请求参数"),
    SIGN_INVALIDATE(402, "参数检验不通过"),
    INVALIDATE_CLIENTID(403, "clientId不合法"),
    NOT_FOUND(404, "所请求资源不存在"),
    PARAM_NOT_REACH(405, "参数不在指定范围内"),
    ID_NULL(406, "ID不可以为空"),
    NO_LOGIN(500, "用户未登录"),
    REQUEST_EXCEED(300, "超出请求次数限制"),
    IO_ERROR(600, "后台处理IO异常"),
    PERMISSION_DENY(700, "无获取该资源的权限"),
    BUSINESS_ERROR(800, "业务处理异常"),
    NEED_RE_LOGIN(900, "权限已变更，请重新登录"),
    SYSTEM_ERROR(999, "系统处理异常"),
    ;

    private ErrorSideEnum errorSideEnum;
    private SubModuleEnum subModuleEnum;
    private int code;
    private String message;

    BaseErrorCode(ErrorSideEnum errorSideEnum, SubModuleEnum subModuleEnum, int code, String msg) {
        this.errorSideEnum = errorSideEnum;
        this.subModuleEnum = subModuleEnum;
        this.code = code;
        this.message = msg;
    }

    BaseErrorCode(int code, String msg) {
        this(ErrorSideEnum.SERVER, SubModuleEnum.BASE, code, msg);
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

        for (BaseErrorCode errorCode : values()) {
            if (errorCode.getDetailCode() == code) {
                return errorCode;
            }
        }
        return UNKNOW;
    }

}
