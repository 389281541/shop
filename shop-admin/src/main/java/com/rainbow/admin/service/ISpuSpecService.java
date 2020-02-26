package com.rainbow.admin.service;

import com.rainbow.admin.api.vo.SpuSpecSimpleVO;
import com.rainbow.admin.entity.SpuImg;
import com.rainbow.admin.entity.SpuSpec;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品基本属性表 服务类
 *
 * @author lujinwei
 * @since 2019-11-17
 */
public interface ISpuSpecService extends IService<SpuSpec> {

    /**
     * 删除Spu_spec关联关系
     * @param spuId
     * @return
     */
    Integer removeBySpuId(Long spuId);

    /**
     * spu规格列表
     * @param spuId
     * @return
     */
    List<SpuSpecSimpleVO> listBySpuId(Long spuId);
}
