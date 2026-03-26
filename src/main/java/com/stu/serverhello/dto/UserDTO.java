package com.stu.serverhello.dto;

/**
 * 数据传输对象：专门接收前端注册/登录的JSON参数
 * 仅包含username/password，避免暴露数据库实体的id等字段
 */
public class UserDTO {
    private String username;
    private String password;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}