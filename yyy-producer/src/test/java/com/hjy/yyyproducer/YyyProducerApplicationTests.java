package com.hjy.yyyproducer;

import com.hjy.yyyproducer.component.RabbitOrderSender;
import com.hjy.yyyproducer.service.OrderService;
import entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class YyyProducerApplicationTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Test
    public void contextLoads() {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setName("测试rabbitMq消息发送");
        order.setMessageId(System.currentTimeMillis() + UUID.randomUUID().toString());
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setId("2018080400000005");
        order.setName("测试创建订单2");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.createOrder(order);
    }
}
