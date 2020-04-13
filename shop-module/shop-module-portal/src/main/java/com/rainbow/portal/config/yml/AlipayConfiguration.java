package com.rainbow.portal.config.yml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置
 *
 * @author lujinwei
 * @since 2020/4/9
 */
@Component
public class AlipayConfiguration {

    @Value("${rainbow.alipay.callback.url}")
    private static String alipayCallbackUrl;

    @Value("${rainbow.alipay.qrCode.path}")
    private static String qrCodePath;

    @Value("${rainbow.alipay.qrCode.qiniuPath}")
    private static String qrCodeQiniuPath;
}
