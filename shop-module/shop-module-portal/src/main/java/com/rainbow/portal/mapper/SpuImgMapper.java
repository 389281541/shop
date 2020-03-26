package com.rainbow.portal.mapper;

import com.rainbow.api.entity.SpuImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 商品图片表 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-03-08
 */
@DS("goods")
public interface SpuImgMapper extends BaseMapper<SpuImg> {

    /**
     * 获取封面图
     * @param list
     * @return
     */
    List<SpuImg> listCoversBySpuIds(@Param("list")Collection<Long> list);


    /**
     * 通过skuIds获取spuImg
     * @param list
     * @return
     */
    List<SpuImg> listBySkuIds(@Param("list")Collection<Long> list);


}
