package com.rainbow.admin.service;

import com.rainbow.api.vo.SpuImgSimpleVO;
import com.rainbow.api.entity.SpuImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品图片表 服务类
 *
 * @author lujinwei
 * @since 2019-12-18
 */
public interface ISpuImgService extends IService<SpuImg> {
    /**
     * 删除某SpuId图片
     * @param spuId
     * @return
     */
    Integer removeBySpuId(Long spuId);

    /**
     * 获取sku图片
     * @param spuId
     * @return
     */
    List<SpuImgSimpleVO> listBySpuId(Long spuId);




    /**
     * 批量插入
     */
    Integer insertBatch(List<SpuImg> list);
}
