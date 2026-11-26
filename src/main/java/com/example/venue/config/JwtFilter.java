package com.example.venue.config;

import com.example.venue.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    // 保存当前登录用户id
    public static ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        // 登录、注册直接跳过JWT校验
        if ("/user/register".equals(path) || "/user/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
        if (token != null && jwtUtil.valid(token)) {
            Long uid = jwtUtil.getUserId(token);
            userIdThreadLocal.set(uid);
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            // 清理，防止线程复用造成数据错乱
            userIdThreadLocal.remove();
        }
    }
}
