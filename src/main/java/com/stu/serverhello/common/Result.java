package com.stu.serverhello.common;
/**
 * 任务3.1 重构后的泛型统一响应体
 * 基于ResultCode枚举实现，前后端分离标准返回格式
 * @param <T> 响应数据泛型，支持任意数据类型
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    // 静态工厂方法：成功回调
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    // 静态工厂方法：失败回调
    public static <T> Result<T> error(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        result.setData(null);
        return result;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }
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