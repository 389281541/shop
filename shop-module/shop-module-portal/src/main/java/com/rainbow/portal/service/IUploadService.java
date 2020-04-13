package com.rainbow.portal.service;

import com.rainbow.api.vo.FileUploadVO;
import com.rainbow.api.vo.UploadImageVO;

import java.io.File;

/**
 * 文件上传服务类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
public interface IUploadService {
    /**
     * 获取七牛云token
     * @return
     */
    FileUploadVO getToken();

    /**
     * 上传文件
     * @param file
     * @return
     */
    UploadImageVO upload(File file);
}
