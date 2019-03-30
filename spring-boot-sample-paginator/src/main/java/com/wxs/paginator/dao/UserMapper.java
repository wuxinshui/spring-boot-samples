package com.wxs.paginator.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxs.paginator.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findByName(@Param("name") String name);

    int insert(@Param("name") String name, @Param("age") Integer age);

    List<User> findAll(PageBounds pageBounds);
}
