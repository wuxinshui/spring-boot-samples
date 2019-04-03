package com.wxs.isolation.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wxs.isolation.entity.User;

public interface UserService extends IService<User> {

    void addUser(User user);

    User selectById(Integer id);


}
