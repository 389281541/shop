package com.rainbow.portal.mq;

import com.google.gson.Gson;
import com.rainbow.api.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送秒杀订单消息
 *
 * @author lujinwei
 * @since 2020/4/23
 */
@Component
@Slf4j
public class FlashMessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendFlashMessage(FlashMessage fm) {
        String msg = new Gson().toJson(fm);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_FLASH.getName(), msg);
    }
}
