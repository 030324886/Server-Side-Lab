package com.stu.serverhello.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.serverhello.common.Result;
import com.stu.serverhello.common.ResultCode;
import com.stu.serverhello.dto.UserDTO;
import com.stu.serverhello.entity.User;
import com.stu.serverhello.mapper.UserMapper;
import com.stu.serverhello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO userDTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userDTO.getUsername());
        User exist = userMapper.selectOne(wrapper);

        if (!Objects.isNull(exist)) {
            // 👇 这里改成枚举实际定义的 USER_HAS_EXISTED
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userDTO.getUsername())
                .eq(User::getPassword, userDTO.getPassword());
        User user = userMapper.selectOne(wrapper);

        if (Objects.isNull(user)) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        return Result.success("登录成功");
    }

    @Override
    public Result<Object> getUserPage(Integer pageNum, Integer pageSize) {
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        Page<User> resultPage = userMapper.selectPage(pageParam, null);
        return Result.success(resultPage);
    }
}