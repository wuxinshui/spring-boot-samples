package com.wxs.isolation.context;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/3 18:56
 */
@Component
@RequestScope
public class AdminInfo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
