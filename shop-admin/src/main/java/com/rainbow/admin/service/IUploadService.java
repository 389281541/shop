package com.rainbow.admin.service;

import com.rainbow.admin.api.vo.FileUploadVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
public interface IUploadService {
    /**
     * 上传图片
     * @param file
     * @return
     */
    FileUploadVO uploadImage(MultipartFile file);
}
