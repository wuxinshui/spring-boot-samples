package com.wxs.tkmybatis.services;

import com.wxs.tkmybatis.mapper.UserMapper;
import com.wxs.tkmybatis.model.User;
import com.wxs.tkmybatis.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public Account getAccount(){
        Account account=new Account();
        account.setCount(new BigDecimal(123456));
        account.setCount1(new BigDecimal(123456.00));
        account.setCount2(new BigDecimal(123456.034));
        account.setCount3(new BigDecimal(123456.3464));


        return account;
    }
}
