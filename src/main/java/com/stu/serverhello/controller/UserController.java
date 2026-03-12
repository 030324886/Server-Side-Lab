package com.stu.serverhello.controller;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * 改造后：统一响应体+异常测试
 * 所有接口返回Result<T>，全局异常拦截测试：故意添加1/0算术异常
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    // GET查询：返回统一响应体，故意添加算术异常（测试全局拦截）
    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable("id") Long id) {
        // 故意写入异常代码：1除以0，触发ArithmeticException
        int a = 1 / 0;
        // 正常返回数据（被异常覆盖，不会执行）
        return Result.success("查询成功，正在返回ID为" + id + "的用户信息");
    }

    // POST新增：返回统一响应体
    @PostMapping
    public Result<String> createUser(@RequestBody com.stu.serverhello.entity.User user) {
        String msg = "新增成功，接收到用户：" + user.getName() + "，年龄：" + user.getAge();
        return Result.success(msg);
    }

    // PUT更新：返回统一响应体
    @PutMapping("/{id}")
    public Result<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        String msg = "更新成功，ID" + id + "的用户已修改为：" + user.getName();
        return Result.success(msg);
    }

    // DELETE删除：返回统一响应体
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long id) {
        String msg = "删除成功，已移除ID为" + id + "的用户";
        return Result.success(msg);
    }
}