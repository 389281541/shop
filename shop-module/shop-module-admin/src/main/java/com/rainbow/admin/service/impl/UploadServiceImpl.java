package com.rainbow.admin.service.impl;

import com.rainbow.admin.api.vo.FileUploadVO;
import com.rainbow.admin.service.IUploadService;
import com.rainbow.admin.util.ImageHandler;
import com.rainbow.common.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
