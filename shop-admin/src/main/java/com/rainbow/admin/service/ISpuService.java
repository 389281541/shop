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
import com.rainbow.common.dto.StatusBatchChangeDTO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
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

    /**
     * 设置上架状态
     * @param param
     * @return
     */
    Boolean setSaleStatus(StatusBatchChangeDTO param);

    /**
     * 设置推荐状态
     * @param param
     * @return
     */
    Boolean setRecommendStatus(StatusBatchChangeDTO param);


    /**
     * spu简单列表
     * @param keyword
     * @return
     */
    List<SpuSimpleVO> listSimple(String keyword);
}
