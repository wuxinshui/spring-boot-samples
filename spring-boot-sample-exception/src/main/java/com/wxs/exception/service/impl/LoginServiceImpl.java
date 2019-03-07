package com.wxs.exception.service.impl;
import com.wxs.exception.common.exception.ApiException;
import com.wxs.exception.entity.User;
import com.wxs.exception.mapper.UserMapper;
import com.wxs.exception.service.LoginService;
import com.wxs.exception.vo.VerifyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public VerifyInfo verify(String username, String pass) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(pass);
        List<User> list = userMapper.select(user);
        VerifyInfo pa = new VerifyInfo();
        pa.setUsername(username);
        int rst = CollectionUtils.isEmpty(list) ? 0 : list.size();
        if (rst > 0) {
            pa.setPass(true);
        }
        return pa;
    }

    @Override
    public Object testApiException()  {
        throw  new ApiException();
    }

    @Override
    public VerifyInfo testOrderNotFoundException(String username, String pass) {
        return null;
    }
}
