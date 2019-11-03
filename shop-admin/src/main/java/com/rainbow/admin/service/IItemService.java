package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.ItemSaveDTO;
import com.rainbow.admin.api.dto.ItemUpdateDTO;
import com.rainbow.admin.api.vo.ItemDetailVO;
import com.rainbow.admin.api.vo.ItemSimpleVO;
import com.rainbow.admin.entity.Item;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.common.dto.PageDTO;

/**
 * 类别表 服务类
 *
 * @author lujinwei
 * @since 2019-08-24
 */
public interface IItemService extends IService<Item> {

    /**
     * 获取类别列表
     * @param param
     * @return
     */
    IPage<ItemSimpleVO> pageItem(IdPageDTO param);

    /**
     * 添加类别
     * @param param
     * @return
     */
    Integer saveItem(ItemSaveDTO param);


    /**
     * 更新类别
     * @param param
     * @return
     */
    Integer updateItem(ItemUpdateDTO param);


    /**
     * 获取类别详情
     * @param param
     * @return
     */
    ItemDetailVO getItemDetailById(IdDTO param);

    /**
     * 删除类别
     * @param param
     * @return
     */
    Integer removeItem(IdDTO param);
}
