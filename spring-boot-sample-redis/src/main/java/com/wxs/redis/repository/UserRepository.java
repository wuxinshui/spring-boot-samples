package com.wxs.redis.repository;

import com.wxs.redis.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author:FujiRen
 * @Date:2017/6/12 16:57
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
