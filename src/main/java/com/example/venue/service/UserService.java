package com.example.venue.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.venue.dto.LoginDTO;
import com.example.venue.dto.RegisterDTO;
import com.example.venue.entity.User;

public interface UserService extends IService<User> {
    String register(RegisterDTO dto);
    String login(LoginDTO dto);  //在接口里额外写的‘register’、‘login’，是继承IService的通用方法满足不了的业务动作。
}
//这是"用户模块"的业务接口：只声明 "用户能注册、能登录"，
//具体怎么校验、怎么存库、怎么发token，写在后面的实现类里。