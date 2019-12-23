package com.hjy.yyyproducer.component;

import com.hjy.yyyproducer.constant.Constants;
import com.hjy.yyyproducer.mapper.BrokerMessageLogMapper;
import entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderSender {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    //自动注入RabbitTemplate模板类
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired(required = false)
    private BrokerMessageLogMapper brokerMessageLogMapper;

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            logger.info("ACK确认成功    correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if (ack) {
                //如果confirm返回成功 则进行更新
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId,
                        Constants.ORDER_SEND_SUCCESS, System.currentTimeMillis());
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                logger.error("异常处理...");
            }
        }
    };


    public void sendOrder(Order order) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange",
                "order.ABC", order, correlationData);
    }
}
