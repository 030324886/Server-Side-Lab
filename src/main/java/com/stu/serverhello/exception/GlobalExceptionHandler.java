package com.stu.serverhello.exception;

import com.stu.serverhello.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获所有通用异常（Exception为所有异常的父类）
    @ExceptionHandler(Exception.class)
    public Result<String> handleAllException(Exception e) {
        // 打印异常日志（便于后端排查问题）
        e.printStackTrace();
        // 返回统一错误响应：状态码500+异常信息
        return Result.error("系统异常：" + e.getMessage());
    }

    // 可扩展：单独捕获特定异常（如空指针、数组越界，按需添加）
    @ExceptionHandler(ArithmeticException.class)
    public Result<String> handleArithmeticException(ArithmeticException e) {
        e.printStackTrace();
        return Result.error("算术异常：除数不能为0");
    }
}