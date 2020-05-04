package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.api.dto.GrouponActivitySaveDTO;
import com.rainbow.api.dto.GrouponActivitySearchDTO;
import com.rainbow.api.dto.GrouponActivityUpdateDTO;
import com.rainbow.api.entity.GrouponActivity;
import com.rainbow.api.vo.GrouponActivityDetailVO;
import com.rainbow.api.vo.GrouponActivitySimpleVO;
import com.rainbow.common.dto.IdDTO;

/**
 * 团购活动服务
 *
 * @author lujinwei
 * @since 2020/5/2
 */
public interface IGrouponActivityService extends IService<GrouponActivity> {

    /**
     * 团购活动分页
     * @param param
     * @return
     */
    IPage<GrouponActivitySimpleVO> pageGrouponActivity(GrouponActivitySearchDTO param);


    /**
     * 添加团购活动
     * @param param
     * @return
     */
    Integer addGrouponActivity(GrouponActivitySaveDTO param);


    /**
     * 移除团购活动
     * @param param
     * @return
     */
    Integer removeGrouponActivity(IdDTO param);


    /**
     * 更新团购活动
     * @param param
     * @return
     */
    Integer updateGrouponActivity(GrouponActivityUpdateDTO param);


    /**
     * 获取团购详情
     * @param param
     * @return
     */
    GrouponActivityDetailVO getGrouponActivityDetailById(IdDTO param);

}
