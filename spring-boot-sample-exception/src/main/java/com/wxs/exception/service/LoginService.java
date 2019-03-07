package com.wxs.exception.service;

import com.wxs.exception.vo.VerifyInfo;

public interface LoginService {
    VerifyInfo verify(String username, String pass);

    Object testApiException();

    VerifyInfo testOrderNotFoundException(String username, String pass);
}
