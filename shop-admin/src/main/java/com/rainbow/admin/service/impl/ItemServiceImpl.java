package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rainbow.admin.api.dto.ItemSaveDTO;
import com.rainbow.admin.api.dto.ItemUpdateDTO;
import com.rainbow.admin.api.vo.ItemDetailVO;
import com.rainbow.admin.api.vo.ItemSimpleVO;
import com.rainbow.admin.api.vo.ItemWithChildrenVO;
import com.rainbow.admin.entity.Item;
import com.rainbow.admin.enums.ItemLevelEnum;
import com.rainbow.admin.mapper.ItemMapper;
import com.rainbow.admin.service.IItemService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;
import com.rainbow.common.enums.BooleanEnum;
import com.rainbow.common.enums.DelFlagEnum;
import com.rainbow.common.vo.IdNameVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    private static final String DEFAULT_PARENTNAME = "无";

    @Override
    public IPage<ItemSimpleVO> pageItem(IdPageDTO param) {
        Page<Item> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Item> condition = new LambdaQueryWrapper<>();
        Long parentId = param.getId();
        ItemLevelEnum type;
        if (parentId == null) {
            type = ItemLevelEnum.PARENT;
        } else {
            condition.eq(Item::getParentId, parentId);
            type = ItemLevelEnum.NON_PARENT;
        }
        condition.eq(Item::getParent, type.getValue());
        condition.orderByAsc(Item::getSortId);
        condition.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
        IPage<Item> items = page(page, condition);
        Item parentItem = baseMapper.selectById(parentId);
        final String parentName = parentItem == null ? DEFAULT_PARENTNAME : parentItem.getName();
        return items.convert(item -> convert2VO(item, parentName));
    }

    @Override
    public List<ItemWithChildrenVO> listWidthSubItem() {
        LambdaQueryWrapper<Item> condition = new LambdaQueryWrapper<>();
        condition.eq(Item::getParent, BooleanEnum.YES.getValue());
        condition.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
        List<Item> parentItemList = baseMapper.selectList(condition);
        Map<Long, List<IdNameVO>> groupMap = Maps.newHashMap();
        if(!CollectionUtils.isEmpty(parentItemList)) {
            List<Long> parentIds = parentItemList.stream().map(Item::getId).collect(Collectors.toList());
            LambdaQueryWrapper<Item> itemWrapper = new LambdaQueryWrapper<>();
            itemWrapper.in(Item::getParentId, parentIds);
            itemWrapper.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
            List<Item> itemList = baseMapper.selectList(itemWrapper);
            groupMap = itemList.stream().collect(Collectors.toMap(Item::getParentId, x->{
                IdNameVO idNameVO = new IdNameVO();
                idNameVO.setId(x.getId());
                idNameVO.setName(x.getName());
                List<IdNameVO> idNameVOList = Lists.newArrayList();
                idNameVOList.add(idNameVO);
                return idNameVOList;
            }, (List<IdNameVO> newList, List<IdNameVO> oldList) -> {
                newList.addAll(oldList);
                return newList;
            }));

        }
        final  Map<Long, List<IdNameVO>> childrenMap = groupMap;
        List<ItemWithChildrenVO> itemWithChildrenVOList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(parentItemList)) {
            itemWithChildrenVOList = parentItemList.stream().map(x -> {
                ItemWithChildrenVO itemVO = new ItemWithChildrenVO();
                itemVO.setId(x.getId());
                itemVO.setName(x.getName());
                itemVO.setChildren(childrenMap.get(x.getId()));
                return itemVO;
            }).collect(Collectors.toList());
        }
        return itemWithChildrenVOList;
    }

    private ItemSimpleVO convert2VO(Item item, String parentName) {
        ItemSimpleVO itemVO = new ItemSimpleVO();
        itemVO.setId(item.getId());
        itemVO.setName(item.getName());
        itemVO.setItemNo(item.getItemNo());
        itemVO.setLevel(ItemLevelEnum.getDesc(item.getParent()));
        itemVO.setCreateTime(item.getCreateTime());
        itemVO.setParent(item.getParent());
        itemVO.setParentName(parentName);
        return itemVO;
    }


    @Override
    public Integer saveItem(ItemSaveDTO param) {
        Long parentId = param.getParentId();
        Item item = new Item();
        item.setParent(Objects.isNull(parentId));
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
        item.setParent(Objects.isNull(param.getParentId()));

        return baseMapper.updateById(item);
    }


    @Override
    public ItemDetailVO getItemDetailById(IdDTO param) {
        ItemDetailVO itemDetailVO = new ItemDetailVO();
        Item item = baseMapper.selectById(param.getId());
        itemDetailVO.setId(item.getId());
        itemDetailVO.setName(item.getName());
        itemDetailVO.setParentId(item.getParentId());
        itemDetailVO.setParent(Objects.isNull(item.getParentId()));
        String parentName = DEFAULT_PARENTNAME;
        if (item.getParentId() != null) {
            Item parentItem = baseMapper.selectById(item.getParentId());
            parentName = parentItem.getName();
        }
        itemDetailVO.setCreateTime(item.getCreateTime());
        itemDetailVO.setParentName(parentName);
        itemDetailVO.setItemNo(item.getItemNo());
        itemDetailVO.setLevel(ItemLevelEnum.getDesc(item.getParent()));
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
        Integer result = baseMapper.selectCount(wrapper);
        return Long.valueOf(result + 1);
    }


    @Override
    public Integer removeItem(IdDTO param) {
        Long itemId = param.getId();
        Item item = baseMapper.selectById(itemId);
        if (item.getParent()) {
            //父类-删除相关联的全部子类
            LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Item::getParentId, item.getParentId());
            wrapper.eq(Item::getDelStatus, item.getDelStatus());
            List<Item> items = baseMapper.selectList(wrapper);
            if (!CollectionUtils.isEmpty(items)) {
                Set<Long> itemIds = items.stream().map(Item::getId).collect(Collectors.toSet());
                baseMapper.deleteBatchIds(itemIds);
            }
        }
        return baseMapper.deleteById(itemId);
    }


    @Override
    public List<ItemSimpleVO> listAllSubItem() {
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Item::getParent,Boolean.FALSE);
        wrapper.eq(Item::getDelStatus, DelFlagEnum.NO.getValue());
        List<Item> items = baseMapper.selectList(wrapper);

        wrapper.eq(Item::getParent,Boolean.TRUE);
        List<Item> parentItems = baseMapper.selectList(wrapper);
        Map<Long, String> parentNameMap = parentItems.stream().collect(Collectors.toMap(Item::getId, Item::getName));

        return items.stream().map(item->
                convert2VO(item,parentNameMap.get(item.getParentId()))
        ).collect(Collectors.toList());
    }
}

