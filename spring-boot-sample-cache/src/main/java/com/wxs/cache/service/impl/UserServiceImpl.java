package com.wxs.cache.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxs.cache.annotation.RedisCacheable;
import com.wxs.cache.entity.User;
import com.wxs.cache.mapper.UserMapper;
import com.wxs.cache.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Cacheable
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void addUser(User user) {
        baseMapper.insert(user);
    }

    @Cacheable(value = "user",key = "#id")
    @Override
    public User selectById(Integer id) {
        return baseMapper.selectById(id);
    }

    @RedisCacheable(value = "user",key = "#id",ttl = 3)
    @Override
    public User selectById2(Integer id) {
        return baseMapper.selectById(id);
    }
}
