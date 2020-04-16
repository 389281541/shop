package com.rainbow.portal.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.api.entity.FlashPromotion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀促销 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-04-14
 */
@DS("goods")
public interface FlashPromotionMapper extends BaseMapper<FlashPromotion> {

    /**
     * 获取今天上线状态的秒杀活动
     * @param status
     * @return
     */
    List<FlashPromotion> listByStatus(@Param("status") Integer status);
}
