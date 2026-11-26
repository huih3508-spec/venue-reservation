package com.example.venue.controller;
import com.example.venue.common.Result;
import com.example.venue.dto.LoginDTO;
import com.example.venue.dto.RegisterDTO;
import com.example.venue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO dto){
        userService.register(dto);
        return Result.success("注册成功");
    }
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto){
        String token = userService.login(dto);
        return Result.success(token);
    }
}
