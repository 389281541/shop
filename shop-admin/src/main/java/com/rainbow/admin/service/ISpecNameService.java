package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.api.dto.SpecNameSaveDTO;
import com.rainbow.admin.api.dto.SpecNameSearchDTO;
import com.rainbow.admin.api.dto.SpecNameUpdateDTO;
import com.rainbow.admin.api.vo.SpecNameDetailVO;
import com.rainbow.admin.api.vo.SpecNameSimpleVO;
import com.rainbow.admin.entity.SpecName;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.common.dto.IdDTO;

/**
 * 规格名称表 服务类
 *
 * @author lujinwei
 * @since 2019-11-17
 */
public interface ISpecNameService extends IService<SpecName> {
    /**
     * 获取属性名列表
     * @param param
     * @return
     */
    IPage<SpecNameSimpleVO> pageSpecName(SpecNameSearchDTO param);

    /**
     * 添加属性名
     * @param param
     * @return
     */
    Integer saveSpecName(SpecNameSaveDTO param);


    /**
     * 更新属性名信息
     * @param param
     * @return
     */
    Integer updateSpecName(SpecNameUpdateDTO param);


    /**
     * 获取属性名详情
     * @param param
     * @return
     */
    SpecNameDetailVO getSpecNameDetailById(IdDTO param);

    /**
     * 删除属性名
     * @param param
     * @return
     */
    Integer removeSpecName(IdDTO param);
}
