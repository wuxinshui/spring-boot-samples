package com.wxs.exception.common.exception;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/11 16:00
 * 异常处理：继承\实现
 */
public class SystemException extends RuntimeException {
    //状态码
    private int code;

    public SystemException( int code,String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
