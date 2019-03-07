package com.wxs.exception.common;

import com.wxs.exception.common.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/11 15:59
 * 异常处理的一种：继承
 */
public class BaseController {

    @ExceptionHandler
    @ResponseBody
    public Result hanlerException(Exception e) {
        if (e instanceof SystemException) {
            return new Result(((SystemException) e).getCode(),e.getMessage());
        } else {
            return  Result.fail();
        }
    }
}
