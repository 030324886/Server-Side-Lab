package com.stu.serverhello.common;

/**
 * 任务3.1 自定义业务状态码枚举类
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    ERROR(500, "系统繁忙，请稍后再试"),
    TOKEN_INVALID(401, "登录过期，请重新登录");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}