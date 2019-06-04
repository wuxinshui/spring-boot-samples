package com.wxs.tomcat.controller.vo;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/4 15:18
 */
public class UserVo {
    private  String name;
    private  String age;
    private  String sex;

    public UserVo() {
    }

    public UserVo(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
