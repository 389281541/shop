package com.rainbow.admin.controller;

import com.rainbow.admin.api.vo.UploadImageVO;
import com.rainbow.admin.util.ImageHandler;
import com.rainbow.common.dto.R;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
@Api(value = "/upload", position = 2, tags = "验证码服务")
public class UploadController {

    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "GET")
    @PostMapping("/image")
    R<UploadImageVO> upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(BaseErrorCode.SIGN_INVALIDATE);
        }
        String path = "";
        try {
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            String key = UUID.randomUUID().toString()
                    .replaceAll("-", "")
                    .toLowerCase();
            path = ImageHandler.uploadImage(fileInputStream, key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (Strings.isBlank(path)) {
            throw new BusinessException(BaseErrorCode.FILE_UPLOAD_OSS_ERROR);
        }
        UploadImageVO uploadImageVO = new UploadImageVO();
        uploadImageVO.setPath(path);
        return new R<>(uploadImageVO);
    }
}
