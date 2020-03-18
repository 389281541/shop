package com.rainbow.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.api.vo.GoodsSimpleVO;
import com.rainbow.api.vo.ShopGoodsVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;

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
