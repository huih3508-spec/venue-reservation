package com.example.venue.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)  //type = IdType.AUTO 作用：主键生成策略为"数据库自增"
    private Long id;
    private String username;
    private String password;
    private String phone;
    private LocalDateTime createTime;
}
