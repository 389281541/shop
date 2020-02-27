package com.rainbow.admin.mapper;

import com.rainbow.admin.api.dto.ShopUpdateDTO;
import com.rainbow.admin.api.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.rainbow.common.dto.StatusChangeDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺表 Mapper 接口
 *
 * @author lujinwei
 * @since 2019-12-04
 */
@DS("goods")
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 更新商铺
     * @param param
     * @return
     */
    Integer updateShop(@Param("param")ShopUpdateDTO param);

    /**
     * 更新审核状态
     * @param param
     * @return
     */
    Integer updateAuditStatus(@Param("param")StatusChangeDTO param);
}
