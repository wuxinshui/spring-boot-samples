package com.wxs.isolation.service;

import com.wxs.isolation.context.LocalMap;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/3 20:20
 */
public interface OauthService {
    ThreadLocal<LocalMap> local = new InheritableThreadLocal<>();

    String getUsername();

    int getUserAge();

    void put(LocalMap localMap);
}
