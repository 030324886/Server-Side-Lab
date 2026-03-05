package com.stu.serverhello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实验附加题1：RESTful API测试接口控制器
 * 基础GET请求接口，访问路径/hello
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String helloApi() {
        return "Hello Server! Spring Boot 3.x RESTful API Test Success!";
    }
}