package com.example.venue.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("verify_code")
public class VerifyCode {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;
    private Long reservationId;
    private Integer isUsed;
    private  LocalDateTime createTime;
    private LocalDateTime useTIme;
}
