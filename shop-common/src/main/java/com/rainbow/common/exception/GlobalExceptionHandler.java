package com.rainbow.common.exception;

import com.rainbow.common.dto.R;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.common.exception.errorcode.UniverseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author lujinwei
 * @since 2018-09-28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Spring自定义异常处理
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.warn("异常信息 ex={}", e.getMessage(), e);

        if (e instanceof BusinessException) {
            UniverseErrorCode errorCodeEnum = ((BusinessException) e).errorCode;
            return new R(errorCodeEnum, e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            //参数格式不合法
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = me.getBindingResult().getAllErrors();
            String errorMsg = errors.get(0).getDefaultMessage();
            return new R<>(BaseErrorCode.PARAM_ERROR, errorMsg, e.getMessage());
        } else if (e instanceof NoSuchMethodException) {
            //调用的方法不存在
            return new R<>(BaseErrorCode.API_NOT_FOUND, e.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            //选择的值不在指定的范围内
            return new R<>(BaseErrorCode.PARAM_NOT_REACH, e.getMessage());
        } else if (e instanceof DuplicateKeyException) {
            //数据库中已存在该记录
            return new R<>(BaseErrorCode.SYSTEM_ERROR, e.getMessage());
        } else {
            //未知错误
            log.error("异常信息 ex={}", e.getMessage(), e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String detailMessage = sw.toString();
            return new R<>(BaseErrorCode.UNKNOW, detailMessage);
        }
    }
}
