package com.rainbow.admin.controller;


import com.rainbow.api.vo.SpuImgSimpleVO;
import com.rainbow.admin.service.ISpuImgService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品图片表 前端控制器
 *
 * @author lujinwei
 * @since 2019-12-18
 */
@RestController
@RequestMapping("/spuImg")
@Api(value = "/spuImg", tags = "spu图片服务")
public class SpuImgController {

    @Autowired
    private ISpuImgService spuImgService;

    @ApiOperation(value = "spu图片表", notes = "spu图片列表", httpMethod = "POST")
    @RequestMapping("/listBySpuId")
    public R<List<SpuImgSimpleVO>> listBySpuId(@Valid @RequestBody IdDTO param) {
        return new R<>(spuImgService.listBySpuId(param.getId()));
    }

}

