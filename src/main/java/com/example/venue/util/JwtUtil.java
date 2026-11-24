package com.example.venue.util;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value(("${jwt.expire}"))
    private int expire;

    private SecretKey getkey(){  //把 "字符串密钥" 变成Java能用的 "印章对象"。签发和验签都要用，故单独包一个小方法复用
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generate(Long userId, String username){  //生成JWT token
        Date now = new Date();  //now：当前时间（签发时间）
        Date expDate = new Date(now.getTime() + expire * 1000);  //expDate：到期时间
        return Jwts.builder()               //开始构建jwt
                .setSubject(username)       //标准载荷sub，存用户名
                .claim("userId",userId)  //自定义载荷，存入用户id（自定义数据）
                .setIssuedAt(now)           //iat，签发时间
                .setExpiration(expDate)     //exp，过期时间
                .signWith(getkey(),SignatureAlgorithm.HS256)  //使 HS256对称加密签名，防止内容被篡改
                .compact();  //拼接成最终的token字符串返回
    }

    public Long getUserId(String token){  //从 token 拿到 userId
        Claims claims = Jwts.parserBuilder().setSigningKey(getkey()).build().parseClaimsJws(token).getBody();
        return claims.get("userId",Long.class);
    }  //构造解析器 → 设置校验密钥 → 解析并校验 token → 取出载荷 → 取出 userId 返回。

    public boolean valid(String token){  //校验 token 是否有效
        try{
            Jwts.parserBuilder().setSigningKey(getkey()).build().parseClaimsJws(token); //构造解析器 → 设置校验密钥 → 解析并校验 token
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
//整体流程总结
//1. 登录成功 → 调用 “generate” 生成 token 返回前端
//2. 前端每次请求，在请求头带上 token
//3. 拦截器拿到 token：先用 “valid” 判断是否有效；有效再调用 “getUserId” 拿到用户 id，完成身份认证
