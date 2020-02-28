package com.rainbow.portal;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 门户项目启动类
 *
 * @author lujinwei
 * @since 2019-06-17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.rainbow.common","com.rainbow.portal"})
@EnableEurekaClient
@EnableSwaggerBootstrapUI
@EnableSwagger2Doc
public class ShopPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopPortalApplication.class);
    }
}
