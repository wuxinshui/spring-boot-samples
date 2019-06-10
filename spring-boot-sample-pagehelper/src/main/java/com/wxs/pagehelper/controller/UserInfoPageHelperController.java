package com.wxs.pagehelper.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxs.pagehelper.dao.UserInfoMapper;
import com.wxs.pagehelper.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/10 19:15
 */
@RestController
@RequestMapping("/pagehelper")
public class UserInfoPageHelperController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/users")
    public PageInfo getUsers(@RequestParam(defaultValue = "1", required = false) int pageNum, @RequestParam(defaultValue = "2", required = false) int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "id desc");
        List<UserInfo> userInfoList = userInfoMapper.selectAll();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        return pageInfo;
    }

}
