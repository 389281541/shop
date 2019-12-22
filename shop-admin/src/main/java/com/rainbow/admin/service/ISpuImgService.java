package com.rainbow.admin.service;

import com.rainbow.admin.entity.SpuImg;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

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
    List<SpuImg> listBySpuId(Long spuId);
}
