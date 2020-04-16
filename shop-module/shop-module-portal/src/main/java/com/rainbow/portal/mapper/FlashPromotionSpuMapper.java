package com.rainbow.portal.mapper;

import com.rainbow.api.entity.FlashPromotionSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 商品限时购与商品关系表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-04-14
 */
@DS("goods")
public interface FlashPromotionSpuMapper extends BaseMapper<FlashPromotionSpu> {

    /**
     * 获取当前
     * @param sessionId
     * @return
     */
    List<FlashPromotionSpu> listBySessionId(@Param("sessionId") Long sessionId, @Param("list")Collection<Long> list);
}
