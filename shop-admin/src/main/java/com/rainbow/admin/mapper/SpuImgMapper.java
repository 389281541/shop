package com.rainbow.admin.mapper;

import com.rainbow.admin.entity.SpuImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 商品图片表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@DS("goods")
public interface SpuImgMapper extends BaseMapper<SpuImg> {

}
