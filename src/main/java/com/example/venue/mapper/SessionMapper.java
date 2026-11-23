package com.example.venue.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.venue.entity.Session;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; //给方法参数起名字，SQL里用 #{名字} 取到它
import org.apache.ibatis.annotations.Update; //标记方法对应的是一条 UPDATE 修改语句

@Mapper
public interface SessionMapper extends BaseMapper<Session> {
    @Update("UPDATE `session` SET remain_quota = remain_quota - 1 WHERE id = #{sessionId} AND status = 1 AND remain_quato > 0") //扣减名额
    int deductQuota(@Param("sessionId") Long sessionId);

    @Update("UPDATE `session` SET remain_quato = remain_quato + 1 WHERE id #{sessionId}" ) //恢复名额
    int backQuota(@Param("sessionId") Long sessionId);

}
