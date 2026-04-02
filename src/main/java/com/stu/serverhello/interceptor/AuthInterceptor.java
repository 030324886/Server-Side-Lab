package com.stu.serverhello.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 放行 OPTIONS 预检请求（跨域用）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 2. 定义白名单：注册、登录、查询接口，无需Token
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/users/register")
                || requestURI.startsWith("/api/users/login")
                || requestURI.startsWith("/api/users/") && request.getMethod().equals("GET")) {
            return true;
        }

        // 3. 敏感操作（PUT/DELETE）必须带Token
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"非法操作：敏感动作需携带登录凭证\"}");
            return false;
        }

        return true;
    }
}