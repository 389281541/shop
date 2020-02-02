package com.rainbow.admin.service;

import com.rainbow.admin.api.vo.SkuSpecSimpleVO;
import com.rainbow.admin.entity.SkuSpec;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * sku属性关联表 服务类
 *
 * @author lujinwei
 * @since 2019-12-21
 */
public interface ISkuSpecService extends IService<SkuSpec> {

    /**
     * 删除skuIds集合
     * @param skuIdList
     * @return
     */
    Integer removeBySkuIds(Collection<Long> skuIdList);

    /**
     * skuId列表
     * @param skuId
     * @return
     */
    List<SkuSpecSimpleVO> listBySkuId(Long skuId);



}
