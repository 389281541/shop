package com.rainbow.portal.mapper;

import com.rainbow.api.entity.ShopWechatInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
/**
 * 团购商铺收款微信信息 Mapper 接口
 *
 * @author lujinwei
 * @since 2020-05-02
 */
@DS("goods")
public interface ShopWechatInfoMapper extends BaseMapper<ShopWechatInfo> {

}
