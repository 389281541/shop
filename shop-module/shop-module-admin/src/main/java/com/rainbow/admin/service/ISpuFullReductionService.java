package com.rainbow.admin.service;

import com.rainbow.api.vo.SpuFullReductionSimpleVO;
import com.rainbow.api.entity.SpuFullReduction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * spu满减表 服务类
 *
 * @author lujinwei
 * @since 2020-02-24
 */
public interface ISpuFullReductionService extends IService<SpuFullReduction> {

    /**
     * 通过spuId查看满减记录
     * @param spuId
     * @return
     */
    List<SpuFullReductionSimpleVO> listBySpuId(Long spuId);


    /**
     * 通过spuId删除满减记录
     * @param spuId
     * @return
     */
    Integer removeBySpuId(Long spuId);

}
