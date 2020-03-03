package com.rainbow.portal.service;

import com.rainbow.api.vo.AdvertiseVO;
import com.rainbow.common.vo.FatherChildrenVO;

import java.util.List;

/**
 * 导航service
 */
public interface INavgationService {

    /**
     * 类别列表
     * @return
     */
    List<FatherChildrenVO> listItem();

    /**
     * 广告信息
     * @return
     */
    AdvertiseVO listAdvertise();
}
