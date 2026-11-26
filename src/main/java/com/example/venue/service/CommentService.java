package com.example.venue.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.venue.dto.CommentDTO;
import com.example.venue.entity.Comment;
import java.util.List;

public interface CommentService extends IService<Comment> {
    void addComment(Long userId, CommentDTO dto); //发表评价
    List<Comment> getByVenueId(Long venueId); //按场馆查评价列表
}
//这是"评价模块的业务接口"：定义两个动作 ——"发表评价、按场馆查评价列表"
//具体逻辑写在"CommentServiceImpl"里。
//返回值void不返回数据，只执行发表动作，且发表动作只关心成功失败，不需要返回实体；错误时抛异常或由全局异常处理兜底。
//为什么返回List：一个场馆对应多条评价，是多条记录，用集合；只查单条才返回单个Comment。
//userId 从后端取，不要让前端传入
//原因：前端传的任何数字都不可信。用户身份只能从“登录后签发的JWT里解析出来“；如果让前端直接传userId，等于把"我是谁"的决定权交给了客户端，伪造一个ID就能冒充任何人。