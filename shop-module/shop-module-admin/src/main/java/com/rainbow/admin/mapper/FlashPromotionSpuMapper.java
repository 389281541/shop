package com.rainbow.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.vo.FlashPromotionSpuVO;
import com.rainbow.api.entity.FlashPromotionSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.common.model.KV;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 商品限时购与商品关系表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-02-21
 */
@DS("goods")
public interface FlashPromotionSpuMapper extends BaseMapper<FlashPromotionSpu> {

    /**
     *  获取秒杀时间段商品数量
     * @param flashPromotionId
     * @return
     */
    List<KV<Long, Long>> getCountMap(@Param("flashPromotionId") Long flashPromotionId);


    /**
     * 分页
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    IPage<FlashPromotionSpuVO> pageFlashSku(IPage<FlashPromotionSpu> page, @Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
