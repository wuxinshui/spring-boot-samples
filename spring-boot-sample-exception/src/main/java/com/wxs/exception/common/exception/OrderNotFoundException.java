package com.wxs.exception.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/1/29 17:39
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")  // 404
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(long id) {
    }
}
