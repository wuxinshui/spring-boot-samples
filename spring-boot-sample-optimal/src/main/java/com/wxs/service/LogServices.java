package com.wxs.service;

import org.springframework.stereotype.Service;

@Service
public class LogServices {

    public boolean saveLog() {
        System.out.println("LogServices saveLog save success");
        return true;
    }
}
