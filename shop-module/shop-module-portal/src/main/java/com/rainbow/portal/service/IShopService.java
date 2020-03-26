package com.rainbow.portal.service;

import com.rainbow.api.vo.ShopGoodsVO;
import com.rainbow.common.dto.IdDTO;

/**
 * shop service
 *
 * @author lujinwei
 * @since 2020/3/10
 */
public interface IShopService {

    /**
     * 获取店铺推荐列表信息
     * @param param
     * @return
     */
    ShopGoodsVO getRecommendGoodsInfo(IdDTO param);
}
