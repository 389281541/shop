package com.rainbow.admin.service;

import com.rainbow.admin.entity.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param spuId
     * @return
     */
    List<Sku> listBySpuId(Long spuId);

    /**
     * 删除某个spu下所有sku
     * @param spuId
     * @return
     */
    Integer removeBySpuId(Long spuId);

}
