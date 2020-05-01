package com.rainbow.portal.mq;

import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.enums.OrderTypeEnum;
import com.rainbow.api.vo.CartPromotionVO;
import com.rainbow.common.util.PortalUtils;
import com.rainbow.portal.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 秒杀消息接受者
 */
@Component
@Slf4j
public class FlashMessageReceiver {

    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = "rainbow.flash")
    public void handle(String message) {
        log.info("receive message:"+message);
        FlashMessage flashMessage = PortalUtils.stringToBean(message, FlashMessage.class);
        List<CartPromotionVO> cartPromotionVOList = flashMessage.getCartPromotionVOList();
        OrderGenerateDTO orderGenerateDTO = new OrderGenerateDTO();
        BeanUtils.copyProperties(flashMessage, orderGenerateDTO);
        String parentOrderNO = flashMessage.getParentOrderNO();
        orderService.generateOrderByShopId(cartPromotionVOList, orderGenerateDTO, parentOrderNO, OrderTypeEnum.FLASH);
        log.info("process parentOrderNO:{}", parentOrderNO);
    }

}
