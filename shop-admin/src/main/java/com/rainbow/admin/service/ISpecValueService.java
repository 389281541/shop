package com.rainbow.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.admin.api.dto.SpecValueSaveDTO;
import com.rainbow.admin.api.dto.SpecValueUpdateDTO;
import com.rainbow.admin.api.dto.UpDownRankingDTO;
import com.rainbow.admin.api.vo.SpecValueDetailVO;
import com.rainbow.admin.api.vo.SpecValuePageVO;
import com.rainbow.admin.api.vo.SpecValueSimpleVO;
import com.rainbow.admin.entity.SpecValue;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.IdPageDTO;

/**
 * 规格值表 服务类
 *
 * @author lujinwei
 * @since 2019-11-17
 */
public interface ISpecValueService extends IService<SpecValue> {

    SpecValuePageVO pageSpecValue(IdPageDTO param);

    Integer saveSpecValue(SpecValueSaveDTO param);

    Integer updateSpecValue(SpecValueUpdateDTO param);

    SpecValueDetailVO getSpecValueDetailById(IdDTO param);

    Integer removeSpecValue(IdDTO param);

    Integer upDownRanking(UpDownRankingDTO param);

}
