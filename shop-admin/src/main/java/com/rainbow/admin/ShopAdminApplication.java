package com.rainbow.admin;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 管理项目启动类
 *
 * @author lujinwei
 * @since 2019-06-17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.rainbow.common","com.rainbow.admin"})
@EnableEurekaClient
@EnableSwaggerBootstrapUI
@EnableSwagger2Doc
public class ShopAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopAdminApplication.class);
    }
}
