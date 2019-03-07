package com.wxs.exception.service.impl;

import com.wxs.exception.common.Result;
import com.wxs.exception.common.exception.SystemException;
import com.wxs.exception.service.InheritExceptionService;
import org.springframework.stereotype.Service;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/11 16:11
 */
@Service
public class InheritExceptionServiceImpl implements InheritExceptionService {
    @Override
    public Result print() {
        throw new SystemException(504,"系统业务异常");
    }
}
