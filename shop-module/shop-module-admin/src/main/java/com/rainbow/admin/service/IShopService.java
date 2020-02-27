package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.ShopSaveDTO;
import com.rainbow.admin.api.dto.ShopSearchDTO;
import com.rainbow.admin.api.dto.ShopUpdateDTO;
import com.rainbow.admin.api.entity.Shop;
import com.rainbow.admin.api.vo.ShopDetailVO;
import com.rainbow.admin.api.vo.ShopSimpleVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.StatusChangeDTO;

/**
 * 店铺表 服务类
 *
 * @author lujinwei
 * @since 2019-12-04
 */
public interface IShopService extends IService<Shop> {

    /**
     * 店铺搜索
     * @param param
     * @return
     */
    IPage<ShopSimpleVO> pageShop(ShopSearchDTO param);

    /**
     * 店铺保存
     * @param param
     * @return
     */
    Integer saveShop(ShopSaveDTO param);

    /**
     * 店铺更新
     * @param param
     * @return
     */
    Integer updateShop(ShopUpdateDTO param);

    /**
     * 获取店铺详情
     * @param param
     * @return
     */
    ShopDetailVO getShopDetailById(IdDTO param);

    /**
     * 删除店铺
     * @param param
     * @return
     */
    Integer removeShop(IdDTO param);

    /**
     * 审核店铺
     * @param param
     * @return
     */
    Integer audit(StatusChangeDTO param);

}
