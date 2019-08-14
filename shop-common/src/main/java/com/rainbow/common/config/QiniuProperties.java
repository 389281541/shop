package com.rainbow.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 七牛属性配置
 *
 * @author lujinwei
 * @since 2019-08-14
 */
@Data
@Component
@ConfigurationProperties(prefix = "store.qiniu")
public class QiniuProperties {

    private String accesskey;

    private String secretkey;

    private String bucket;

    private String domain;

}
