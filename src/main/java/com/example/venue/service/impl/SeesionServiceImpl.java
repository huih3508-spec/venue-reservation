package com.example.venue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.venue.entity.Session;
import com.example.venue.mapper.SessionMapper;
import com.example.venue.service.SessionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeesionServiceImpl extends ServiceImpl<SessionMapper, Session> implements SessionService {
    @Override
    public List<Session> listByVenueId(Long venueId){
        LambdaQueryWrapper<Session> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Session::getVenueId, venueId).eq(Session::getStatus,1);
        return list(wrapper);  //list(条件)：ServiceImpl自带方法，按条件查多行，返回List<Session>；
    }
}
