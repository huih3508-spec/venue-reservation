package com.example.venue.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.venue.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {
}
