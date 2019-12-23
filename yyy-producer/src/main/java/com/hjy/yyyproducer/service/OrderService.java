package com.hjy.yyyproducer.service;

import com.hjy.yyyproducer.component.RabbitOrderSender;
import com.hjy.yyyproducer.mapper.BrokerMessageLogMapper;
import com.hjy.yyyproducer.mapper.OrderMapper;
import com.hjy.yyyproducer.util.FastJsonConvertUtil;
import entity.BrokerMessageLog;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private BrokerMessageLogMapper brokerMessageLogMapper;


    public void createOrder(Order order) {
        long currentTimeMillis = System.currentTimeMillis();
        orderMapper.insert(order);
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        // 保存消息整体 转为JSON 格式存储入库
        brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
        brokerMessageLog.setTryCount(0);
        // 设置消息状态为0 表示发送中
        brokerMessageLog.setStatus("0");
        // 设置消息未确认超时时间窗口为 一分钟
        brokerMessageLog.setNextRetry(System.currentTimeMillis() + 60 * 1000);
        brokerMessageLog.setCreateTime(System.currentTimeMillis());
        brokerMessageLog.setUpdateTime(System.currentTimeMillis());
        brokerMessageLogMapper.insert(brokerMessageLog);
        // 发送消息
        rabbitOrderSender.sendOrder(order);
    }


}
