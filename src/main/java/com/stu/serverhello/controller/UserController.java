package com.stu.serverhello.controller;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.entity.User;
import org.springframework.web.bind.annotation.*;
/**
 * 任务3.1 重构后：基于自定义状态码的统一响应体
 * 所有接口返回Result<T>，适配前后端分离业务状态码规范
 * 接口前缀：/api/users
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable("id") Long id) {
        String data = "查询成功，正在返回ID为" + id + "的用户信息";
        return Result.success(data);
    }

    // 2. POST新增用户：
    @PostMapping
    public Result<String> createUser(@RequestBody User user) {
        String data = "新增成功，接收到用户：" + user.getName() + "，年龄：" + user.getAge();
        return Result.success(data);
    }
    @PutMapping("/{id}")
    public Result<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        String data = "更新成功，ID" + id + "的用户已修改为：" + user.getName();
        return Result.success(data);
    }
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long id) {
        String data = "删除成功，已移除ID为" + id + "的用户";
        return Result.success(data);
    }
    @PostMapping("/login")
    public Result<String> login() {
        String data = "登录成功，已生成有效Token";
        return Result.success(data);
    }
}