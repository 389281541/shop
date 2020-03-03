package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.dto.FlashPromotionSpuSaveDTO;
import com.rainbow.api.dto.FlashPromotionSpuUpdateDTO;
import com.rainbow.api.dto.FlashSpuSearchDTO;
import com.rainbow.api.vo.FlashPromotionSpuVO;
import com.rainbow.api.entity.FlashPromotionSpu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.dto.IdDTO;

import java.util.List;
import java.util.Map;

/**
 * 商品限时购与商品关系表 服务类
 *
 * @author lujinwei
 * @since 2020-02-21
 */
public interface IFlashPromotionSpuService extends IService<FlashPromotionSpu> {

    /**
     * 获取数量
     * @param flashPromotionId
     * @return
     */
    Map<Long, Long> getCountMap(Long flashPromotionId);


    /**
     * 秒杀商品分页
     * @param param
     * @return
     */
    IPage<FlashPromotionSpuVO> pageFlashSpu(FlashSpuSearchDTO param);


    /**
     * 批量添加秒杀商品
     * @param param
     * @return
     */
    Boolean addBatch( List<FlashPromotionSpuSaveDTO> param);

    /**
     * 更新秒杀商品
     * @param param
     * @return
     */
    Integer updateFlashPromotionSpu(FlashPromotionSpuUpdateDTO param);


    /**
     * 删除秒杀商品
     * @param param
     * @return
     */
    Integer removeFlashPromotionSpu(IdDTO param);


    /**
     * 获取秒杀商品详情
     * @param param
     * @return
     */
    FlashPromotionSpuVO getFlashPromotionSpuDetailById(IdDTO param);
}
