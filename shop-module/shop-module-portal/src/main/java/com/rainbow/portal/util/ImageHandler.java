package com.rainbow.portal.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.rainbow.portal.config.yml.QiniuConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * 图片工具类
 *
 * @author lujinwei
 * @since 2019-08-14
 */
@Slf4j
public class ImageHandler {

    private static String bucket = QiniuConfiguration.bucket;

    private static String domain = QiniuConfiguration.domain;

    private static Auth auth = QiniuConfiguration.getAuth();

    public final static Zone zone = Zone.zone1();


    public static String uploadImage(InputStream inputStream, String key) {
        try {

            Configuration cfg = new Configuration(zone);
            UploadManager uploadManager = new UploadManager(cfg);
            String upToken = auth.uploadToken(bucket, key);

            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            log.info("response: " + JSON.toJSONString(response));
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return domain + "/" + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.info(r.toString());
            try {
                log.info(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
                log.error(ex2.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getToken(String key) {
        return auth.uploadToken(bucket, key, 3600, null);
    }
}
