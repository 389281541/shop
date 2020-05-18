package com.rainbow.portal.config.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置
 *
 * @author lujinwei
 * @since 2020/4/9
 */
@Component
@Data
@ConfigurationProperties(prefix="rainbow.alipay")
public class AliPayConfiguration {

    private String appId;//在后台获取（必须配置）

    private String merchantPrivateKey;

    private String alipayPublicKey;

    private String notifyUrl;

    private String returnUrl;

    private String signType;

    private String charset;
    //注意：沙箱测试环境，正式环境为：https://openapi.alipay.com/gateway.do
    private String gatewayUrl;
}
