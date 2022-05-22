package com.wxs.cache.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wxs.cache.entity.User;

public interface UserService extends IService<User> {

    void addUser(User user);

    User selectById(Integer id);

    User selectById2(Integer id);


}
