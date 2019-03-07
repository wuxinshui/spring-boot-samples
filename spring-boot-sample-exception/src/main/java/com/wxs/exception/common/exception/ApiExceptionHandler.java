package com.wxs.exception.common.exception;

import com.wxs.exception.common.Result;
import com.wxs.exception.common.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/1/7 16:19
 */
@ControllerAdvice
public class ApiExceptionHandler extends RuntimeException
{
    public ApiExceptionHandler() {
        super();
    }

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public Result handler(ApiException e){
        return new Result(ResultCode.BIZ_ERR);
    }
}
