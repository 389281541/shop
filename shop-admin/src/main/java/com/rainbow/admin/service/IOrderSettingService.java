package com.rainbow.admin.service;

import com.rainbow.admin.api.dto.OrderSettingDTO;
import com.rainbow.admin.api.vo.OrderSettingVO;
import com.rainbow.admin.entity.OrderSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.dto.IdDTO;

/**
 * 订单设置表 服务类
 *
 * @author lujinwei
 * @since 2020-02-18
 */
public interface IOrderSettingService extends IService<OrderSetting> {

    /**
     * 获取订单设置
     * @param param
     * @return
     */
    OrderSettingVO getDetailById(IdDTO param);


    /**
     * 更新订单设置
     * @param param
     * @return
     */
    Integer updateOrderSetting(OrderSettingDTO param);

}
