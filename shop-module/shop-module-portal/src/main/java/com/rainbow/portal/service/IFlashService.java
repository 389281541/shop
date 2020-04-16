package com.rainbow.portal.service;

import com.rainbow.api.vo.FlashThemeVO;

import java.util.List;

/**
 * 秒杀服务
 *
 * @author lujinwei
 * @since 2020/4/14
 */
public interface IFlashService {

    /**
     * 秒杀主题列表
     * @return
     */
    List<FlashThemeVO> listFlashThemes();
}
