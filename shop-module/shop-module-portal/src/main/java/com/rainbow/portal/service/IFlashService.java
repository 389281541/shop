package com.rainbow.portal.service;

import com.rainbow.api.dto.GoConfirmOrderDTO;
import com.rainbow.api.dto.OrderGenerateDTO;
import com.rainbow.api.vo.ConfirmOrderVO;
import com.rainbow.api.vo.FlashCurrentSessionVO;
import com.rainbow.api.vo.FlashThemeVO;

import java.util.List;

/**
 * 秒杀服务
 *
 * @author lujinwei
 * @since 2020/4/14
 */
public interface IFlashService {

    /**
     * 秒杀主题列表
     * @return
     */
    List<FlashThemeVO> listFlashThemes();

    /**
     * 获取当前session
     * @return
     */
    FlashCurrentSessionVO getCurrentPromotionSession();

    /**
     * 生成秒杀订单
     * @param customerId
     * @return
     */
    ConfirmOrderVO generateFlashConfirmOrder(Long customerId);

    /**
     * 秒杀
     * @param param
     * @return
     */
    Boolean goConfirmOrder(GoConfirmOrderDTO param);


    /**
     * 生成秒杀订单
     * @param param
     * @return
     */
    Boolean generateFlashOrder(OrderGenerateDTO param);
}
