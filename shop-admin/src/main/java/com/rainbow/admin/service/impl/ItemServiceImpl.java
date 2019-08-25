package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.api.dto.ItemDTO;
import com.rainbow.admin.api.vo.ItemSimpleVO;
import com.rainbow.admin.entity.Item;
import com.rainbow.admin.enums.ItemLevelEnum;
import com.rainbow.admin.mapper.ItemMapper;
import com.rainbow.admin.service.IItemService;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.common.dto.PageDTO;
import com.rainbow.common.enums.DelFlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 类别表 服务实现类
 *
 * @author lujinwei
 * @since 2019-08-24
 */
@Slf4j
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public IPage<ItemSimpleVO> pageParentItem(PageDTO param) {
        Page<Item> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Item> condition = new LambdaQueryWrapper<>();
        condition.eq(Item::getParent, ItemLevelEnum.PARENT.getValue());
        condition.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
        condition.orderByAsc(Item::getSortId);
        return pageItem(page, condition, ItemLevelEnum.PARENT);
    }

    @Override
    public IPage<ItemSimpleVO> pageSubItem(IdPageDTO param) {
        Page<Item> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Item> condition = new LambdaQueryWrapper<>();
        condition.eq(Item::getParentId, param.getId());
        condition.eq(Item::getParent, ItemLevelEnum.NON_PARENT.getValue());
        condition.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
        condition.orderByAsc(Item::getSortId);
        return pageItem(page, condition, ItemLevelEnum.NON_PARENT);
    }

    private IPage<ItemSimpleVO> pageItem(Page<Item> page, LambdaQueryWrapper<Item> condition, ItemLevelEnum itemLevel) {
        IPage<Item> parentItems = page(page, condition);
        return parentItems.convert(item -> {
            ItemSimpleVO itemVO = new ItemSimpleVO();
            itemVO.setId(item.getId());
            itemVO.setName(item.getName());
            itemVO.setItemNo(item.getItemNo());
            itemVO.setLevel(itemLevel.getDesc());
            itemVO.setSortId(item.getSortId());
            return itemVO;
        });
    }


    @Override
    public Integer saveItem(ItemDTO param) {
        Item item = new Item();
        item.setName(param.getName());
        item.setItemNo(param.getItemNo());
        item.setCreateTime(LocalDateTime.now());
        item.setDelStatus(DelFlagEnum.NO.getValue());
        item.setParentId(param.getParentId());
        if (param.getParentId() == null) {
            item.setParent(Boolean.FALSE);
        } else {
            item.setParent(Boolean.TRUE);
        }

        return baseMapper.insert(item);
    }


    @Override
    public Integer updateItem(ItemDTO param) {
        Item item = new Item();
        item.setId(param.getId());
        item.setName(param.getName());
        item.setParentId(param.getParentId());
        item.setItemNo(param.getItemNo());
        if(param.getParentId() != null) {
            item.setParent(Boolean.TRUE);
        } else {
            item.setParent(Boolean.FALSE);
        }

        return baseMapper.updateById(item);
    }
}

