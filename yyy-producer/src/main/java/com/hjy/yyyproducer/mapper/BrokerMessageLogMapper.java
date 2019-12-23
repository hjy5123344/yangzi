package com.hjy.yyyproducer.mapper;

import entity.BrokerMessageLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrokerMessageLogMapper {

    /**
     * 查询消息状态为0(发送中) 且已经超时的消息集合
     * @return
     */
    List<BrokerMessageLog> query4StatusAndTimeoutMessage();

    /**
     * 重新发送统计count发送次数 +1
     * @param messageId
     * @param updateTime
     */
    void update4ReSend(@Param("messageId") String messageId, @Param("updateTime") Long updateTime);
    /**
     * 更新最终消息发送结果 成功 or 失败
     * @param messageId
     * @param status
     * @param updateTime
     */
    void changeBrokerMessageLogStatus(@Param("messageId") String messageId, @Param("status") String status, @Param("updateTime") Long updateTime);



    int insert(BrokerMessageLog record);



}
