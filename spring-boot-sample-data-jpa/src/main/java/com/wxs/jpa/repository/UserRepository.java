package com.wxs.jpa.repository;

import com.wxs.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 16:57
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
