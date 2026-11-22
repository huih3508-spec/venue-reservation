package com.example.venue.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("session")
public class Session {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long venueId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalQuota;
    private Integer remainQuota;
    private Integer status;
}
