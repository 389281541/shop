package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.FlashPromotionSession;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 限时购场次表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@DS("goods")
public interface FlashPromotionSessionMapper extends BaseMapper<FlashPromotionSession> {

}
