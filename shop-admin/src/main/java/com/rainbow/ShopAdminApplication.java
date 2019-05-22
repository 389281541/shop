package com.rainbow;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Admin启动类
 *
 * @author lujinwei
 * @Date 2019-05-20 20:51
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2Doc
@EnableSwaggerBootstrapUI
public class ShopAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopAdminApplication.class, args);
    }

}
