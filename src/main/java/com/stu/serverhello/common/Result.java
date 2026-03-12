package com.stu.serverhello.common;

/**
 * 附加题1 企业级统一响应体
 * 泛型设计：支持任意类型数据返回
 * 包含：状态码(code)、提示信息(msg)、核心数据(data)
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    // 私有构造器：禁止外部直接实例化，通过静态方法调用
    private Result() {}

    // 成功响应：无数据返回（仅返回状态+提示）
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    // 成功响应：带数据返回（常用，如查询结果、新增对象）
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    // 失败响应：自定义错误信息
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    // 失败响应：自定义状态码+错误信息（扩展用）
    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    // Getter & Setter 方法
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}