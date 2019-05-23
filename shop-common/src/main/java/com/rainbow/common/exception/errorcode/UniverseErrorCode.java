package com.rainbow.common.exception.errorcode;

/**
 * 统一异常类定义
 *
 * @author lujinwei
 * @Date 2018/11/9 16:08
 */
public interface UniverseErrorCode {

    /**
     * 问题侧：区分问题发生在客户端还是服务器端
     *
     * @return
     */
    ErrorSideEnum getErrorSideEnum();

    /**
     * 区分问题发生在哪个子系统
     *
     * @return
     */
    SubModuleEnum getSubSystem();

    /**
     * 区分问题发生在哪个子模块
     *
     * @return
     */
    SubModuleEnum getSubModule();

    /**
     * 获取子系统定义的错误码，每个子系统自己定义
     *
     * @return
     */
    int getDetailCode();

    /**
     * 获取错误描述，和子系统错误码一一对应
     *
     * @return
     */
    String getMessage();

    /**
     * 通过错误码获取对应的错误对象
     *
     * @param code
     * @return
     */
    UniverseErrorCode fromCode(int code);

}
