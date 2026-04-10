package com.stu.serverhello.service;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.dto.UserDTO;

public interface UserService {
    // 注册
    Result<String> register(UserDTO userDTO);

    // 登录
    Result<String> login(UserDTO userDTO);
    //分页
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);
}