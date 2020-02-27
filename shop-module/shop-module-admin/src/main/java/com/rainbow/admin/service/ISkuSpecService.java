package com.rainbow.admin.service;

import com.rainbow.admin.api.vo.SkuSpecSimpleVO;
import com.rainbow.admin.api.entity.SkuSpec;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.vo.MemberShipVO;

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
     * 通过skuId获取属性值列表
     * @param skuId
     * @return
     */
    List<SkuSpecSimpleVO> listBySkuId(Long skuId);


    /**
     *
     * @param spuId
     * @return
     */
    List<SkuSpecSimpleVO> listBySpuId(Long spuId);


    /**
     * 通过spuId获取属性值 并通过specNameId分组了
     * @param spuId
     * @return
     */
    List<MemberShipVO> listMemberShipBySpuId(Long spuId);


    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertBatch(List<SkuSpec> list);

}
