package com.example.venue.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.venue.entity.Session;
import java.util.List;

public interface SessionService extends IService<Session> {
    List<Session> listByVenueId(Long venueId); //按场馆ID查询场次列表
}
//这是"场次模块"的业务接口：声明 "按场馆ID 查询场次列表"，
//具体逻辑写在"SessionServiceImpl"里。