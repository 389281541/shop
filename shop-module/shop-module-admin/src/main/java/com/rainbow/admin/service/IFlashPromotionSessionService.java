package com.rainbow.admin.service;

import com.rainbow.api.dto.FlashPromotionSessionSaveDTO;
import com.rainbow.api.dto.FlashPromotionSessionUpdateDTO;
import com.rainbow.api.vo.FlashPromotionSessionVO;
import com.rainbow.api.entity.FlashPromotionSession;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.StatusChangeDTO;

import java.util.List;

/**
 * 限时购场次表 服务类
 *
 * @author lujinwei
 * @since 2020-02-21
 */
public interface IFlashPromotionSessionService extends IService<FlashPromotionSession> {

    /**
     * 秒杀时间段分页
     * @return
     */
    List<FlashPromotionSessionVO> listFlashPromotionSession();


    /**
     * 有效秒杀时间段分页
     * @param param
     * @return
     */
    List<FlashPromotionSessionVO> listAvailable(IdDTO param);


    /**
     * 添加秒杀时间段
     * @param param
     * @return
     */
    Integer addFlashPromotionSession(FlashPromotionSessionSaveDTO param);


    /**
     * 移除秒杀时间段
     * @param param
     * @return
     */
    Integer removeFlashPromotionSession(IdDTO param);


    /**
     * 更新秒杀时间段
     * @param param
     * @return
     */
    Integer updateFlashPromotionSession(FlashPromotionSessionUpdateDTO param);


    /**
     * 获取秒杀时间段详情
     * @param param
     * @return
     */
    FlashPromotionSessionVO getFlashPromotionSessionDetailById(IdDTO param);

    /**
     * 更改状态
     * @param param
     * @return
     */
    Integer updateStatus(StatusChangeDTO param);
}
