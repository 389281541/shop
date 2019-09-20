package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.vo.BrandSimpleVO;
import com.rainbow.admin.entity.Brand;
import com.rainbow.common.dto.IdNamePageDTO;

/**
 * 品牌表 服务类
 *
 * @author lujinwei
 * @since 2019-08-27
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 品牌分页列表
     * @param param
     * @return
     */
    IPage<BrandSimpleVO> pageBrandByItem(IdNamePageDTO param);

    Boolean addBrand();
}
