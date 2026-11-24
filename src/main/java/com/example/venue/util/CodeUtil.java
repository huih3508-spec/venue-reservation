package com.example.venue.util;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class CodeUtil {
    public String getVerifyCode(){  //随机号码生成器
        return UUID.randomUUID().toString().replace("-","").substring(0,32); //加工一个随机串 作用：下单时调一下，吐出一个别人猜不到的随机码，当核销凭证。
    }
}

