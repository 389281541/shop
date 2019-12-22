package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.SpuSaveDTO;
import com.rainbow.admin.api.dto.SpuSearchDTO;
import com.rainbow.admin.api.dto.SpuUpdateDTO;
import com.rainbow.admin.api.vo.SpuDetailVO;
import com.rainbow.admin.api.vo.SpuSimpleVO;
import com.rainbow.admin.entity.Spu;
import com.rainbow.common.dto.IdDTO;

/**
 * 商品表 服务类
 *
 * @author lujinwei
 * @since 2019-12-08
 */
public interface ISpuService extends IService<Spu> {
    /**
     * SPU分页列表
     * @param param
     * @return
     */
    IPage<SpuSimpleVO> pageSpu(SpuSearchDTO param);

    /**
     * 添加SPU
     * @param param
     * @return
     */
    Integer addSpu(SpuSaveDTO param);

    /**
     * 移除SPU
     * @param param
     * @return
     */
    Boolean removeSpu(IdDTO param);

    /**
     * 更新SPU
     * @param param
     * @return
     */
    Boolean updateSpu(SpuUpdateDTO param);

    /**
     * 获取SPU详情
     */
    SpuDetailVO getSpuDetailById(IdDTO param);
}
