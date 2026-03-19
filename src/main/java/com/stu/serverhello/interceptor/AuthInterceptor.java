/*package com.stu.serverhello.interceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.PrintWriter;
 * 任务3.2 自定义鉴权拦截器
 * 实现HandlerInterceptor接口，重写preHandle前置拦截方法
 * 校验请求头中的Authorization令牌，无令牌则拦截请求

public class AuthInterceptor implements HandlerInterceptor {
     * 前置拦截方法：请求到达Controller前执行
     * @return true=放行，false=拦截

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头中获取令牌（Authorization）
        String token = request.getHeader("Authorization");
        // 2. 令牌校验：无令牌则拦截，返回401自定义JSON
        if (token == null || token.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            String errorJson = "{\"code\": 401, \"msg\": \"登录凭证已缺失，请重新登录\"}";
            // 向前端写入错误响应
            PrintWriter writer = response.getWriter();
            writer.write(errorJson);
            writer.flush();
            writer.close();
            return false;
        }
        // 3. 令牌存在，放行请求到Controller
        return true;
    }
}*/
package com.stu.serverhello.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

/**
 * 附加题1 重构后：基于HTTP动词的精细化鉴权拦截器
 * 结合request.getMethod()和getRequestURI()实现细粒度放行
 * 仅放行POST-/api/users、GET-/api/users/*，其余敏感操作需Token
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取本次请求的HTTP动词
        String method = request.getMethod();
        String uri = request.getRequestURI();
        // 细粒度放行规则（核心：HTTP动词+URL双重判断）
        // 规则A：POST请求 + 路径精确等于/api/users → 放行（新增用户/注册）
        boolean isCreateUser = "POST".equalsIgnoreCase(method) && "/api/users".equals(uri);
        // 规则B：GET请求 + 路径以/api/users/开头 → 放行（查询单个用户）
        boolean isGetUser = "GET".equalsIgnoreCase(method) && uri.startsWith("/api/users/");
        // 满足任一放行规则，直接放行，无需校验Token
        if (isCreateUser || isGetUser) {
            return true;
        }
        // 敏感操作（PUT/DELETE等）：执行严格的Token校验
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            // 错误信息携带敏感操作的HTTP动词，便于前端排查
            String errorJson = "{\"code\": 401, \"msg\": \"非法操作：敏感动作[" + method + "]需携带登录凭证\"}";
            PrintWriter writer = response.getWriter();
            writer.write(errorJson);
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }
}