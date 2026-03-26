package com.stu.serverhello.controller;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.dto.UserDTO;
import com.stu.serverhello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器层：重构后仅保留核心业务接口
 * 依赖注入UserService，不直接处理业务逻辑，只做参数接收和响应返回
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    // 注入业务层接口（Spring自动装配实现类UserServiceImpl）
    @Autowired
    private UserService userService;

    // 1. 用户注册：POST /api/users（拦截器已放行）
    @PostMapping
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // 2. 用户登录：POST /api/users/login（拦截器已放行）
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    // 3. 查询用户信息：GET /api/users/{id}（拦截器已放行，用于测试）
    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable("id") Long id) {
        return Result.success("查询成功，正在返回ID为" + id + "的用户信息");
    }

    // 可选：保留删除/更新接口，用于拦截器敏感操作测试
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long id) {
        return Result.success("删除成功，已移除ID为" + id + "的用户");
    }

    @PutMapping("/{id}")
    public Result<String> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return Result.success("更新成功，ID" + id + "的用户已修改为：" + userDTO.getUsername());
    }
}