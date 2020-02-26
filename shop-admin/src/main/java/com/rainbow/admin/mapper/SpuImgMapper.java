package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.SpuImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品图片表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@DS("goods")
public interface SpuImgMapper extends BaseMapper<SpuImg> {

    /**
     * 删除某个spuId图片
     * @param spuId
     * @return
     */
    Integer removeBySpuId(@Param("spuId")Long spuId);


    /**
     * 批量插入spuImg
     * @param list
     * @return
     */
    Integer insertBatch(@Param("list")List<SpuImg> list);

}
