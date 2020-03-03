package com.rainbow.admin.service;

import com.rainbow.api.vo.FileUploadVO;

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
}
