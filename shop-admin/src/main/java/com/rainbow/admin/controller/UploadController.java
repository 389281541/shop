package com.rainbow.admin.controller;

import com.rainbow.admin.api.vo.FileUploadVO;
import com.rainbow.admin.service.IUploadService;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
@Api(value = "/upload", tags = "上传服务")
public class UploadController {

    @Autowired
    private IUploadService uploadService;


    @ApiOperation(value = "", notes = "获取七牛云上传Token", httpMethod = "GET")
    @GetMapping("/getToken")
    R<FileUploadVO> getToken() {
        return new R<>(uploadService.getToken());
    }

}
