package com.wxs.aop.service;

import com.wxs.aop.vo.VerifyInfo;

public interface LoginService {
    VerifyInfo verify(String username, String pass);
}
