package com.stu.serverhello.service.impl;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.common.ResultCode;
import com.stu.serverhello.dto.UserDTO;
import com.stu.serverhello.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 业务层实现类：实现UserService接口的业务逻辑
 * @Service：将该类交给Spring容器管理，供Controller注入
 * 实验要求：暂时用Map模拟数据库，后续替换为UserMapper
 */
@Service
public class UserServiceImpl implements UserService {
    // 模拟数据库：key=用户名，value=密码
    private static final Map<String, String> userDb = new HashMap<>();

    @Override
    public Result<String> register(UserDTO userDTO) {
        // 1. 校验：用户名是否已存在
        String username = userDTO.getUsername();
        if (userDb.containsKey(username)) {
            // 返回自定义状态码：用户已存在
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }
        // 2. 注册成功：存入模拟数据库
        userDb.put(username, userDTO.getPassword());
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        // 1. 校验：用户是否存在
        if (!userDb.containsKey(username)) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        // 2. 校验：密码是否正确
        String dbPwd = userDb.get(username);
        if (!dbPwd.equals(password)) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        // 3. 登录成功：生成随机Token（UUID），返回给前端
        String token = "Bearer " + UUID.randomUUID().toString().replace("-", "");
        return Result.success(token); // Token存入data中返回
    }
}