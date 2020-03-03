package com.rainbow.portal.controller;

import com.rainbow.common.dto.R;
import com.rainbow.common.vo.FatherChildrenVO;
import com.rainbow.api.vo.AdvertiseVO;
import com.rainbow.portal.service.INavgationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nav")
@Api(value = "/nav", tags = "导航页服务")
public class NavigationController {

    @Autowired
    private INavgationService navgationService;

    @ApiOperation(value = "类别列表", notes = "类别列表", httpMethod = "POST")
    @PostMapping("/listItem")
    public R<List<FatherChildrenVO>> listItem() {
        return new R<>(navgationService.listItem());
    }

    @ApiOperation(value = "类别列表", notes = "类别列表", httpMethod = "POST")
    @PostMapping("/listAdvertise")
    public R<AdvertiseVO> listAdvertise() {
        return new R<>(navgationService.listAdvertise());
    }
}
