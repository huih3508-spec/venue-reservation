package com.example.venue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.venue.dto.LoginDTO;
import com.example.venue.dto.RegisterDTO;
import com.example.venue.entity.User;
import com.example.venue.mapper.UserMapper;
import com.example.venue.service.UserService;
import com.example.venue.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {  //继承MP基类，白拿数据库通用方法
    @Autowired  //Spring 的依赖注入：容器里如果已经有这个类型的对象，就自动赋给这个字段，不用自己 `new`
    private JwtUtil jwtUtil;  //JWT工具类，之前已标 @Component，容器里有它
    @Autowired
    private BCryptPasswordEncoder encoder;  //BCrypt加密器，用来加密密码、比对密码

               //@Override：重写接口 UserService 的 register方法
    @Override  //表示 "这个方法是兑现接口里的承诺"。加上它，编译器帮你检查签名有没有写错，仅此而已，不参与业务逻辑
    public String register(RegisterDTO dto){  //目标：新用户存进数据库，密码加密后存
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User exist = getOne(wrapper);
        if(exist != null){
            throw new RuntimeException("用户已存在");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        save(user);
        return "注册成功";
    }

    @Override
    public String login(LoginDTO dto){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, dto.getUsername());
        User user = getOne(wrapper);
        if(user == null || !encoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("账号密码错误");
        }
        return jwtUtil.generate(user.getId(), user.getUsername());
    }
}
