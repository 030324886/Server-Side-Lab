package com.stu.serverhello.config;
import com.stu.serverhello.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 任务3.2 拦截器配置类
 * 加@Configuration注解，接管Spring MVC底层配置
 * 挂载自定义AuthInterceptor，配置拦截/放行规则
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/api/**") // 拦截/api下的所有请求（多级路径）
                .excludePathPatterns(
                        "/api/users/login"
                      //  "/api/users",
                //"/api/users/*"
                );
    }
}