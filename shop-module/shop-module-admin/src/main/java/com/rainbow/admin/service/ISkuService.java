package com.rainbow.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.SkuBatchUpdateDTO;
import com.rainbow.admin.api.vo.SkuSimpleVO;
import com.rainbow.admin.api.entity.Sku;
import com.rainbow.common.dto.IdSearchDTO;

import java.util.List;

/**
 * 商品sku表 服务类
 *
 * @author lujinwei
 * @since 2019-12-18
 */
public interface ISkuService extends IService<Sku> {

    /**
     * 查询某个spu下sku列表
     * @param param
     * @return
     */
    List<SkuSimpleVO> listBySpuId(IdSearchDTO param);

    /**
     * 删除某个spu下所有sku
     * @param spuId
     * @return
     */
    Integer removeBySpuId(Long spuId);


    /**
     * 批量更新sku
     * @param param
     * @return
     */
    Boolean updateBatch(SkuBatchUpdateDTO param);

}
