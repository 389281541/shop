package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.ItemDTO;
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
     * 获取一级菜单列表
     * @param param
     * @return
     */
    IPage<ItemSimpleVO> pageParentItem(PageDTO param);


    /**
     * 根据一级菜单ID查看子菜单ID
     * @param param
     * @return
     */
    IPage<ItemSimpleVO> pageSubItem(IdPageDTO param);

    /**
     * 添加类别
     * @param param
     * @return
     */
    Integer saveItem(ItemDTO param);


    /**
     * 更新类别
     * @param param
     * @return
     */
    Integer updateItem(ItemDTO param);
}
