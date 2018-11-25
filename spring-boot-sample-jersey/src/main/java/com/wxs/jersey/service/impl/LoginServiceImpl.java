package com.wxs.jersey.service.impl;

import com.wxs.jersey.entity.User;
import com.wxs.jersey.mapper.UserMapper;
import com.wxs.jersey.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int verify(String username, String pass) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(pass);
        List<User> list = userMapper.select(user);
        return CollectionUtils.isEmpty(list) ? 0 : list.size();
    }
}
