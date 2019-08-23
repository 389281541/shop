package com.rainbow.admin.config.yml;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 七牛属性配置
 *
 * @author lujinwei
 * @since 2019-08-14
 */
@Component
public class QiniuProperty {

    private static String accesskey;

    private static String secretkey;

    public static String bucket;

    public static String domain;


    @Value("${store.qiniu.accesskey}")
    public void setAccessKey(String accessKey) {
        this.accesskey = accessKey;
    }

    @Value("${store.qiniu.secretkey}")
    public void setSecretKey(String secretKey) {
        this.secretkey = secretKey;
    }

    @Value("${store.qiniu.domain}")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Value("${store.qiniu.bucket}")
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }


    public static Auth getAuth() {
        return Auth.create(accesskey, secretkey);
    }

}
