package com.example.venue.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;// "BCryptPasswordEncoder"："Spring Security"提供的密码加密工具，单向加密，适合存用户密码
import org.springframework.security.web.SecurityFilterChain;

@Configuration  //标记这是一个配置类
@EnableWebSecurity  //开启web安全配置
public class SecurityConfig {

    // 密码加密Bean
    @Bean //方法上的注解，把这个方法返回的对象注册成 Spring 的"Bean"
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // 安全过滤链：在这里放行接口
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 放行注册、登录，不用认证
                        .requestMatchers("/user/register", "/user/login").permitAll()
                        // 其他请求都需要认证
                        .anyRequest().authenticated()
                )
                // 关闭csrf（前后端分离项目一般关掉）
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}

//这个"SecurityConfig"是配置类，作用：把"BCryptPasswordEncoder（BCrypt 密码加密器）"交给 Spring容器，变成一个 Bean，
// 后面我们就可以用 "@Autowired" 注入拿来加密密码。
