package com.rainbow.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.api.dto.HomeAdvertiseSaveDTO;
import com.rainbow.api.dto.HomeAdvertiseSearchDTO;
import com.rainbow.api.dto.HomeAdvertiseUpdateDTO;
import com.rainbow.api.entity.HomeAdvertise;
import com.rainbow.api.enums.HomeAdvertiseTypeEnum;
import com.rainbow.api.vo.AdvertiseVO;
import com.rainbow.api.vo.HomeAdvertiseVO;
import com.rainbow.admin.mapper.HomeAdvertiseMapper;
import com.rainbow.admin.service.IHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.common.dto.IdDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 轮播图表 服务实现类
 *
 * @author lujinwei
 * @since 2020-03-03
 */
@Service
public class HomeAdvertiseServiceImpl extends ServiceImpl<HomeAdvertiseMapper, HomeAdvertise> implements IHomeAdvertiseService {


    @Override
    public IPage<HomeAdvertiseVO> pageHomeAdvertise(HomeAdvertiseSearchDTO param) {
        LambdaQueryWrapper<HomeAdvertise> wrapper = new LambdaQueryWrapper<>();
        if (param.getType() != null) {
            wrapper.eq(HomeAdvertise::getType, param.getType());
        }
        if (param.getStatus() != null) {
            wrapper.eq(HomeAdvertise::getStatus, param.getStatus());
        }
        Page<HomeAdvertise> pageInfo = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<HomeAdvertise> homeAdvertiseIPage = baseMapper.selectPage(pageInfo, wrapper);
        return homeAdvertiseIPage.convert(x->{
            HomeAdvertiseVO homeAdvertiseVO = new HomeAdvertiseVO();
            BeanUtils.copyProperties(x, homeAdvertiseVO);
            return homeAdvertiseVO;
        });
    }


    @Override
    public HomeAdvertiseVO getDetailHomeAdvertiseById(IdDTO param) {
        HomeAdvertiseVO homeAdvertiseVO = new HomeAdvertiseVO();
        HomeAdvertise homeAdvertise = baseMapper.selectById(param.getId());
        BeanUtils.copyProperties(homeAdvertise, homeAdvertiseVO);
        return homeAdvertiseVO;
    }


    @Override
    public Integer addHomeAdvertise(HomeAdvertiseSaveDTO param) {
        HomeAdvertise homeAdvertise = new HomeAdvertise();
        BeanUtils.copyProperties(param, homeAdvertise);
        homeAdvertise.setCreateTime(LocalDateTime.now());
        return baseMapper.insert(homeAdvertise);
    }

    @Override
    public Integer updateHomeAdvertise(HomeAdvertiseUpdateDTO param) {
        HomeAdvertise homeAdvertise = new HomeAdvertise();
        BeanUtils.copyProperties(param, homeAdvertise);
        homeAdvertise.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(homeAdvertise);
    }


    @Override
    public Integer removeHomeAdvertise(IdDTO param) {
        return baseMapper.deleteById(param.getId());
    }
}
