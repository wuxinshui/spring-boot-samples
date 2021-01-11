package com.wxs.mybatis.mapper;

import com.wxs.mybatis.interceptor.EncryptField;
import com.wxs.mybatis.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    int insertList(List<User> users);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @EncryptField
    @Select("select * from t_user where username=#{username}")
    User selectByName(@EncryptField String username);

    @EncryptField
    @Select("select password from t_user where username=#{username}")
    String selectPasswordByName(@EncryptField String username);
}