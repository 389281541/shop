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
     * 获取全部当前时间段的秒杀商品
     * @param sessionId
     * @return
     */
    List<FlashPromotionSpu> listBySessionId(@Param("sessionId") Long sessionId, @Param("list")Collection<Long> list);

    /**
     * 获取当前spu是否处于秒杀状态
     * @param sessionId
     * @param list
     * @param spuId
     * @return
     */
    FlashPromotionSpu getFlashPromotionSpu(@Param("sessionId") Long sessionId, @Param("list")Collection<Long> list, @Param("spuId")Long spuId);


    /**
     * 更新秒杀商品库存
     * @param sessionId
     * @param flashPromotionId
     * @param spuId
     * @return
     */
    Integer updateFlashSpuStock(@Param("sessionId") Long sessionId, @Param("flashPromotionId") Long flashPromotionId, @Param("spuId") Long spuId, @Param("quantity") Integer quantity);
}
