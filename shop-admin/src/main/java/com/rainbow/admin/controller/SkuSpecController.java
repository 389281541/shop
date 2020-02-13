package com.rainbow.admin.controller;


import com.rainbow.admin.api.vo.SkuSpecSimpleVO;
import com.rainbow.admin.service.ISkuSpecService;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.vo.MemberShipVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * sku属性关联表 前端控制器
 *
 * @author lujinwei
 * @since 2019-12-21
 */
@RestController
@RequestMapping("/skuSpec")
@Api(value = "/skuSpec", tags = "skuSpec服务")
public class SkuSpecController {

    @Autowired
    private ISkuSpecService skuSpecService;

    @ApiOperation(value = "sku规格表", notes = "品牌列表", httpMethod = "POST")
    @PostMapping("/listMemberShipBySpuId")
    public R<List<MemberShipVO>> listBySpuId(@Valid @RequestBody IdDTO param) {
        return new R<>(skuSpecService.listMemberShipBySpuId(param.getId()));
    }

}

