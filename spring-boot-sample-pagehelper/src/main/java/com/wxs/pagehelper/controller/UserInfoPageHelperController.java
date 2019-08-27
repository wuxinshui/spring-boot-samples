package com.wxs.pagehelper.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxs.pagehelper.dao.UserInfoMapper;
import com.wxs.pagehelper.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增
     *
     * @param userInfo
     */
    @PostMapping("/users/add")
    public void addUsers(@RequestBody UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    /**
     * 更新
     *
     * @param userInfo
     */
    @PostMapping("/users/update")
    public void updateUsers(@RequestBody UserInfo userInfo) {
        //根据主键更新
        userInfoMapper.updateByPrimaryKey(userInfo);
    }

}
