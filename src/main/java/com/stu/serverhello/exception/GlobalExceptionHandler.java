package com.stu.serverhello.exception;

import com.stu.serverhello.common.Result;
import com.stu.serverhello.common.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 全局异常处理器
 * @RestControllerAdvice：拦截所有@RestController的异常，返回JSON格式
 * 统一捕获工程中所有未处理的异常，避免暴露代码堆栈
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获所有通用异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleAllException(Exception e) {
        // 打印异常日志
        e.printStackTrace();
        return new Result<>(ResultCode.ERROR.getCode(), "系统异常：" + e.getMessage(), null);
    }

    //单独捕获特定异常（如空指针、数组越界，按需添加）
    @ExceptionHandler(ArithmeticException.class)
    public Result<String> handleArithmeticException(ArithmeticException e) {
        e.printStackTrace();
        return new Result<>(ResultCode.ERROR.getCode(), "算术异常：除数不能为0", null);
    }
}