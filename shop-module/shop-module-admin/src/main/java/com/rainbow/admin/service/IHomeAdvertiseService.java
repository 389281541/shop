package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.HomeAdvertiseSaveDTO;
import com.rainbow.api.dto.HomeAdvertiseSearchDTO;
import com.rainbow.api.dto.HomeAdvertiseUpdateDTO;
import com.rainbow.api.entity.HomeAdvertise;
import com.rainbow.api.vo.HomeAdvertiseVO;
import com.rainbow.common.dto.IdDTO;

/**
 * 轮播图表 服务类
 *
 * @author lujinwei
 * @since 2020-03-03
 */
public interface IHomeAdvertiseService extends IService<HomeAdvertise> {

    /**
     * 广告位列表
     * @return
     */
    IPage<HomeAdvertiseVO> pageHomeAdvertise(HomeAdvertiseSearchDTO param);


    /**
     * 获取广告信息详情
     * @param param
     * @return
     */
    HomeAdvertiseVO getDetailHomeAdvertiseById(IdDTO param);


    /**
     * 添加首页广告
     * @param param
     * @return
     */
    Integer addHomeAdvertise(HomeAdvertiseSaveDTO param);


    /**
     * 更新首页广告位
     * @param param
     * @return
     */
    Integer updateHomeAdvertise(HomeAdvertiseUpdateDTO param);


    /**
     * 移除首页广告
     * @param param
     * @return
     */
    Integer removeHomeAdvertise(IdDTO param);
}
