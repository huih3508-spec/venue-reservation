package com.example.venue.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.venue.entity.Venue;
import com.example.venue.mapper.VenueMapper;
import com.example.venue.service.VenueService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VenueServiceImpl extends ServiceImpl<VenueMapper, Venue> implements VenueService {
    @Override  //标明这是重写接口里的方法，编译器会核对方法名、参数、返回值是否和接口一致
    public List<Venue> listAllVenue(){
        LambdaQueryWrapper<Venue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Venue::getStatus, 1);
        return list(wrapper);
    }
}
