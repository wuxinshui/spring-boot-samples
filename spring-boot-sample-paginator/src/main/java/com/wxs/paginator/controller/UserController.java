package com.wxs.paginator.controller;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wxs.paginator.dao.UserMapper;
import com.wxs.paginator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/3/29 22:11
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public PageList<User> test() {

        userMapper.insert("小米", 21);
        userMapper.insert("小兰", 22);
        userMapper.insert("小四", 23);
        userMapper.insert("张三", 24);
        userMapper.insert("李四", 25);
        userMapper.insert("王五", 26);
        userMapper.insert("泽六", 27);
        PageBounds pageBounds = new PageBounds(1, 5, Order.formString("age.desc"));
        PageList<User> users = (PageList<User>) userMapper.findAll(pageBounds);
        System.out.println("--------------------page-------------------------");
        System.out.println(users);
        System.out.println("总数量：" + users.getPaginator().getTotalCount());
        System.out.println("总页数：" + users.getPaginator().getTotalPages());
        System.out.println("当前页：" + users.getPaginator().getPage());
        System.out.println("每页条数：" + users.getPaginator().getLimit());
        System.out.println("--");

        return users;
    }
}
