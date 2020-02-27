package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.FlashPromotionSaveDTO;
import com.rainbow.admin.api.dto.FlashPromotionUpdateDTO;
import com.rainbow.admin.api.entity.FlashPromotion;
import com.rainbow.admin.api.vo.FlashPromotionVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.SearchPageDTO;
import com.rainbow.common.dto.StatusChangeDTO;

/**
 * 秒杀促销 服务类
 *
 * @author lujinwei
 * @since 2020-02-21
 */
public interface IFlashPromotionService extends IService<FlashPromotion> {

    /**
     * 秒杀活动列表
     * @param param
     * @return
     */
    IPage<FlashPromotionVO> pageFlashPromotion(SearchPageDTO param);


    /**
     * 添加秒杀活动
     * @param param
     * @return
     */
    Integer addFlashPromotion(FlashPromotionSaveDTO param);


    /**
     * 删除秒杀活动
     * @param param
     * @return
     */
    Integer removeFlashPromotion(IdDTO param);


    /**
     * 更新秒杀活动
     * @param param
     * @return
     */
    Integer updateFlashPromotion(FlashPromotionUpdateDTO param);



    /**
     * 更新秒杀活动
     * @param param
     * @return
     */
    Integer updateStatus(StatusChangeDTO param);


    /**
     * 获取秒杀活动详情
     * @param param
     * @return
     */
    FlashPromotionVO getFlashPromotionDetailById(IdDTO param);
}
