package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.dto.IdPageDTO;

/**
 * 品牌表 服务类
 *
 * @author lujinwei
 * @since 2019-08-27
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 品牌分页列表
     * @param idPageDTO
     * @return
     */
    IPage<BrandSimpleVO> pageBrandByItem(IdPageDTO idPageDTO);
}
