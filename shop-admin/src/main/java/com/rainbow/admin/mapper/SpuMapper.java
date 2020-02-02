package com.rainbow.admin.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.admin.api.dto.SpuSearchDTO;
import com.rainbow.admin.entity.Spu;
import com.rainbow.common.dto.StatusBatchChangeDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 商品表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-08
 */
@DS("goods")
public interface SpuMapper extends BaseMapper<Spu> {

    /**
     * 分页展示spu
     * @param param
     * @return
     */
    IPage<Spu> pageSpu(Page<Spu> page, @Param("param")SpuSearchDTO param);


    /**
     * 批量更新上架状态
     * @param param
     * @return
     */
    Integer updateSaleStatus(@Param("param") StatusBatchChangeDTO param);

    /**
     * 批量更新推荐状态
     * @param param
     * @return
     */
    Integer updateRecommendStatus(@Param("param")StatusBatchChangeDTO param);


}
