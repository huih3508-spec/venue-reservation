package com.example.venue.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.venue.entity.Venue;
import java.util.List;

public interface VenueService extends IService<Venue> {
    List<Venue> listAllVenue(); //查全部场馆
}
//这是 "场馆模块" 的业务接口：声明 "能查询所有场馆"，
//具体查询逻辑写在后面的'VenueServiceImpl'实现类里。