package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.admin.api.dto.ItemSaveDTO;
import com.rainbow.admin.api.dto.ItemUpdateDTO;
import com.rainbow.admin.api.vo.ItemDetailVO;
import com.rainbow.admin.api.vo.ItemSimpleVO;
import com.rainbow.admin.entity.Item;
import com.rainbow.admin.enums.ItemLevelEnum;
import com.rainbow.admin.mapper.ItemMapper;
import com.rainbow.admin.service.IItemService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.common.dto.PageDTO;
import com.rainbow.common.enums.DelFlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            return itemVO;
        });
    }


    @Override
    public Integer saveItem(ItemSaveDTO param) {
        Long parentId = param.getParentId();
        Item item = new Item();
        if (parentId == null) {
            item.setParent(Boolean.TRUE);
        } else {
            item.setParent(Boolean.FALSE);
        }
        item.setName(param.getName());
        item.setItemNo(param.getItemNo());
        item.setCreateTime(LocalDateTime.now());
        item.setDelStatus(DelFlagEnum.NO.getValue());
        item.setParentId(param.getParentId());
        item.setSortId(getSortId(parentId));
        return baseMapper.insert(item);
    }


    @Override
    public Integer updateItem(ItemUpdateDTO param) {

        Long parentId = param.getParentId();
        Item item = new Item();
        item.setId(param.getId());
        item.setName(param.getName());
        item.setParentId(parentId);
        item.setItemNo(param.getItemNo());
        item.setSortId(getSortId(parentId));
        if (param.getParentId() != null) {
            item.setParent(Boolean.TRUE);
        } else {
            item.setParent(Boolean.FALSE);
        }

        return baseMapper.updateById(item);
    }


    @Override
    public ItemDetailVO getItemDetailById(IdDTO param) {
        ItemDetailVO itemDetailVO = new ItemDetailVO();
        Item item = baseMapper.selectById(param.getId());
        itemDetailVO.setId(item.getId());
        itemDetailVO.setName(item.getName());
        itemDetailVO.setParentId(item.getParentId());
        itemDetailVO.setItemNo(item.getItemNo());
        itemDetailVO.setLevel(item.getParent()?ItemLevelEnum.PARENT.getDesc():ItemLevelEnum.NON_PARENT.getDesc());
        return itemDetailVO;
    }


    /**
     * 根据当前类别获取排序ID
     *
     * @param parentId
     * @return
     */
    private Long getSortId(Long parentId) {
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        if (parentId == null) {
            wrapper.eq(Item::getParent, Boolean.TRUE);
        } else {
            wrapper.eq(Item::getParentId, parentId);
        }
        Integer result =  baseMapper.selectCount(wrapper);
        return Long.valueOf(result+1);
    }


    @Override
    public Integer removeItem(IdDTO param) {
        Long itemId = param.getId();
        Item item = baseMapper.selectById(itemId);
        if(item.getParent()) {   //父类-删除相关联的全部子类
            LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Item::getParentId, item.getParentId());
            wrapper.eq(Item::getDelStatus, item.getDelStatus());
            List<Item> items = baseMapper.selectList(wrapper);
            if(!CollectionUtils.isEmpty(items)) {
                Set<Long> itemIds = items.stream().map(Item::getId).collect(Collectors.toSet());
                baseMapper.deleteBatchIds(itemIds);
            }
        }
        return baseMapper.deleteById(itemId);
    }
}

