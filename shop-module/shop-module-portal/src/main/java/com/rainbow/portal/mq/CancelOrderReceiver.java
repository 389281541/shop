package com.rainbow.portal.mq;

import com.rainbow.portal.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 */
@Component
@RabbitListener(queues = "rainbow.order.cancel")
@Slf4j
public class CancelOrderReceiver {

    @Autowired
    private IOrderService orderService;

    @RabbitHandler
    public void handle(Long orderId) {
        orderService.cancelOrder(orderId);
        orderService.returnCustomerIntegration(orderId);
        log.info("process orderId:{}", orderId);
    }
}
