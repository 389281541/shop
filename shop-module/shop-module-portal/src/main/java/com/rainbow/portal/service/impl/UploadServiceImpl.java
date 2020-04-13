package com.rainbow.portal.service.impl;

import com.rainbow.api.vo.UploadImageVO;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import com.rainbow.portal.service.IUploadService;
import com.rainbow.portal.util.ImageHandler;
import com.rainbow.api.vo.FileUploadVO;
import com.rainbow.common.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

/**
 * 上传服务实现类
 *
 * @author lujinwei
 * @since 2019-08-25
 */
@Slf4j
@Service
public class UploadServiceImpl implements IUploadService {

    @Override
    public FileUploadVO getToken() {
        FileUploadVO fileUploadVO = new FileUploadVO();
        String key = UUIDUtils.getLowerCaseUUID();
        fileUploadVO.setKey(key);
        fileUploadVO.setToken(ImageHandler.getToken(key));
        fileUploadVO.setUrl(ImageHandler.getDomain());
        return fileUploadVO;
    }


    @Override
    public UploadImageVO upload(File file) {
        if(file == null) {
            throw new BusinessException(BaseErrorCode.SIGN_INVALIDATE);
        }
        String path = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String key = UUID.randomUUID().toString()
                    .replaceAll("-", "")
                    .toLowerCase();
            path = ImageHandler.uploadImage(fileInputStream, key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(Strings.isBlank(path)) {
            throw new BusinessException(BaseErrorCode.FILE_UPLOAD_OSS_ERROR);
        }
        UploadImageVO uploadImageVO = new UploadImageVO();
        uploadImageVO.setPath(path);
        return uploadImageVO;
    }
}
