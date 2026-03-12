package com.stu.serverhello.entity;

/**
 * 任务2.1 实体类：用户信息
 * 包含name/id/age属性，及必备的构造、get/set方法
 */
public class User {
    private Long id;       // 用户ID
    private String name;   // 用户名
    private Integer age;   // 年龄

    // 无参构造器（Spring JSON解析必须）
    public User() {
    }

    // 全参构造器
    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter & Setter 方法（JSON序列化/反序列化必须）
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}