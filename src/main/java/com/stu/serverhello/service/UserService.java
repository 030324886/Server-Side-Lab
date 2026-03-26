package com.stu.serverhello.service;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.dto.UserDTO;

/**
 * 业务层接口：定义用户注册/登录的业务方法
 * 面向接口编程，解耦实现层
 */
public interface UserService {
    // 用户注册
    Result<String> register(UserDTO userDTO);
    // 用户登录（返回Token）
    Result<String> login(UserDTO userDTO);
}