package com.rainbow.admin.mapper;

import com.rainbow.admin.api.dto.SkuBatchUpdateDTO;
import com.rainbow.admin.api.dto.SkuUpdateDTO;
import com.rainbow.admin.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品sku表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@DS("goods")
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 删除spuId名下sku
     * @param spuId
     * @return
     */
    Integer removeBySpuId(@Param("spuId")Long spuId);

    /**
     * 批量更新sku
     * @param list
     * @return
     */
    Integer updateBatch(@Param("list") List<SkuUpdateDTO> list);
}
