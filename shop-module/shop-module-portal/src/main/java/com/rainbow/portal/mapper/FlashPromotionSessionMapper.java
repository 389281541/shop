package com.rainbow.portal.mapper;

import com.rainbow.api.entity.FlashPromotionSession;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.time.LocalTime;
import java.util.List;

/**
 * 限时购场次表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-04-14
 */
@DS("goods")
public interface FlashPromotionSessionMapper extends BaseMapper<FlashPromotionSession> {

    /**
     * 秒杀片段
     * @return
     */
    List<FlashPromotionSession> listFlashSession();

    /**
     * 获取最近未来时间段
     * @return
     */
    FlashPromotionSession getRecentSession(@Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);
}
