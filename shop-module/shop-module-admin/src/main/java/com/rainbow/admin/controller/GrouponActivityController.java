package com.rainbow.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.admin.service.IGrouponActivityService;
import com.rainbow.api.dto.GrouponActivitySaveDTO;
import com.rainbow.api.dto.GrouponActivitySearchDTO;
import com.rainbow.api.dto.GrouponActivityUpdateDTO;
import com.rainbow.api.vo.GrouponActivityDetailVO;
import com.rainbow.api.vo.GrouponActivitySimpleVO;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 团购活动
 *
 * @author lujinwei
 * @since 2020/5/2
 */
@RestController
@RequestMapping("/grouponActivity")
@Api(value = "/grouponActivity", tags = "团购活动页")
public class GrouponActivityController {

    @Autowired
    private IGrouponActivityService grouponActivityService;

    @ApiOperation(value = "团购活动列表", notes = "团购活动列表", httpMethod = "POST")
    @PostMapping("/page")
    public R<IPage<GrouponActivitySimpleVO>> page(@Valid @RequestBody GrouponActivitySearchDTO param) {
        return new R<>(grouponActivityService.pageGrouponActivity(param));
    }

    @ApiOperation(value = "添加团购活动", notes = "添加团购活动", httpMethod = "POST")
    @PostMapping("/add")
    public R<Boolean> add(@Valid @RequestBody GrouponActivitySaveDTO param) {
        Integer result = grouponActivityService.addGrouponActivity(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "删除团购活动", notes = "删除团购活动", httpMethod = "POST")
    @PostMapping("/remove")
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer result = grouponActivityService.removeGrouponActivity(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "修改团购活动", notes = "修改团购活动", httpMethod = "POST")
    @PostMapping("/update")
    public R<Boolean> update(@Valid @RequestBody GrouponActivityUpdateDTO param) {
        Integer result = grouponActivityService.updateGrouponActivity(param);
        return new R<>(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "团购活动详情", notes = "团购活动详情", httpMethod = "POST")
    @PostMapping("/detail")
    public R<GrouponActivityDetailVO> detail(@Valid @RequestBody IdDTO param) {
        return new R<>(grouponActivityService.getGrouponActivityDetailById(param));
    }


}
