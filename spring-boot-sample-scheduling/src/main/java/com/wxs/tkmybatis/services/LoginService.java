package com.wxs.tkmybatis.services;

import com.wxs.tkmybatis.mapper.UserMapper;
import com.wxs.tkmybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>LoginService</p>
 *
 * @author wuxinshui
 */
@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public int login(String username, String password) throws Exception{
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            List<User> users = userMapper.select(user);
            return null == users ? 0 : users.size();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
