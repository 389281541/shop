package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.ItemSaveDTO;
import com.rainbow.api.dto.ItemUpdateDTO;
import com.rainbow.api.vo.ItemDetailVO;
import com.rainbow.api.vo.ItemSimpleVO;
import com.rainbow.api.vo.ItemWithChildrenVO;
import com.rainbow.api.entity.Item;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;

import java.util.List;

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

    /**
     * 获取父类及其子类别子类类别
     * @return
     */
    List<ItemWithChildrenVO> listWidthSubItem();

    /**
     * 获取所有子类别
     * @return
     */
    List<ItemSimpleVO> listAllSubItem();
}
