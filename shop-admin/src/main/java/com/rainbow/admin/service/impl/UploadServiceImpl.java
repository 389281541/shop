package com.rainbow.admin.service.impl;

import com.rainbow.admin.api.vo.FileUploadVO;
import com.rainbow.admin.service.IUploadService;
import com.rainbow.admin.util.ImageHandler;
import com.rainbow.common.exception.BusinessException;
import com.rainbow.common.exception.errorcode.BaseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
    public FileUploadVO uploadImage(MultipartFile file) {
        FileUploadVO fileUploadVO = new FileUploadVO();
        if (file.isEmpty()) {
            throw new BusinessException(BaseErrorCode.SIGN_INVALIDATE);
        }
        fileUploadVO.setFileName(file.getName());
        try {
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            String key = UUID.randomUUID().toString()
                    .replaceAll("-", "")
                    .toLowerCase();
            fileUploadVO.setObjectKey(key);
            byte[] bytes = file.getBytes();
            if (bytes != null) {
                fileUploadVO.setSize(Long.valueOf(bytes.length));
            }
            String url = ImageHandler.uploadImage(fileInputStream, key);
            fileUploadVO.setUrl(url);

            BufferedImage image = ImageIO.read(fileInputStream);
            //如果image=null 表示上传的不是图片格式
            if (image != null) {
                fileUploadVO.setWidth(image.getWidth());
                fileUploadVO.setHeight(image.getHeight());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return fileUploadVO;
    }
}
