package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.FlashPromotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 秒杀促销 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@DS("goods")
public interface FlashPromotionMapper extends BaseMapper<FlashPromotion> {

}
