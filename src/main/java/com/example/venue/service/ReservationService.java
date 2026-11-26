package com.example.venue.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.venue.dto.ReserveDTO;
import com.example.venue.entity.Reservation;

public interface ReservationService extends IService<Reservation>{
    String reserve(Long userId, ReserveDTO dto); //提交预约
    boolean verifyCode(String code); //核销校验
    void releaseExpireReservation(); //释放超时订单
}
//这是"预约订单模块"的业务接口，定义三个核心动作："提交预约、核销校验、释放超时订单"
//具体逻辑写在"ReservationServiceImpl"里。
//返回值void不返回数据，只执行动作
//方法名'releaseExpireReservation'释放超时未核销订单