package com.wxs.exception.common.exception;

import com.wxs.exception.common.Result;
import com.wxs.exception.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/1/23 16:26
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    @ResponseBody
    Result handler(Exception e) {
        log.error("handler exception error ", e);
        return new Result(ResultCode.PARAM_ERR);
    }

    /**
     * Bean validation
     * ControllerAdvice 统一返回错误消息
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class})
    @ResponseBody
    Result validateBean(BindException e) {
        List<String> validateResult = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        log.error("validateBean exception error ", e);
        return new Result(ResultCode.PARAM_ERR.getCode(), validateResult.toString());
    }

    /**
     * ControllerAdvice 校验字段
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    Result validateField(ConstraintViolationException e) {
        log.error("validateBean exception error ", e);
        return new Result(ResultCode.PARAM_ERR.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    Result handlerException(Exception e) {
        log.error("handlerException exception error ", e);
        return new Result(ResultCode.SERVER_ERR);
    }
}
