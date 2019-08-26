package com.rainbow.admin.controller;

import com.rainbow.admin.api.vo.FileUploadVO;
import com.rainbow.admin.service.IUploadService;
import com.rainbow.common.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
@Api(value = "/upload", tags = "验证码服务")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "GET")
    @PostMapping("/image")
    R<FileUploadVO> uploadImage(MultipartFile file) {
        return new R<>(uploadService.uploadImage(file));
    }
}
