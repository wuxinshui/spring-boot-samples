package com.wxs.exception.service.impl;

import org.springframework.stereotype.Service;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/22 15:51
 */
@Service
public class InitializingBeanService {

    public void initialPrint() {
        System.out.println("InitializingBeanService:");
    }

}
