package com.example.venue.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// "BCryptPasswordEncoder"："Spring Security"提供的密码加密工具，单向加密，适合存用户密码

@Configuration  //标记这是一个配置类
public class SecurityConfig {
    // 密码加密Bean
    @Bean //方法上的注解，把这个方法返回的对象注册成 Spring 的"Bean"
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
//这个"SecurityConfig"是配置类，作用：把"BCryptPasswordEncoder（BCrypt 密码加密器）"交给 Spring容器，变成一个 Bean，
// 后面我们就可以用 "@Autowired" 注入拿来加密密码。
