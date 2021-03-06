package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.BrandSaveDTO;
import com.rainbow.api.dto.BrandUpdateDTO;
import com.rainbow.api.vo.BrandDetailVO;
import com.rainbow.api.vo.BrandSimpleVO;
import com.rainbow.api.entity.Brand;
import com.rainbow.common.dto.IdDTO;
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

    /**
     * 添加品牌
     * @param param
     * @return
     */
    Integer addBrand(BrandSaveDTO param);

    /**
     * 移除品牌
     * @param param
     * @return
     */
    Integer removeBrand(IdDTO param);

    /**
     * 更新品牌
     * @param param
     * @return
     */
    Boolean updateBrand(BrandUpdateDTO param);

    /**
     * 获取品牌详情
     */
    BrandDetailVO getBrandDetailById(IdDTO param);

}
