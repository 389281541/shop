package com.rainbow.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 基于eureka到注册中心
 *
 * @author lujinwei
 * @Date 2019-05-21 16:06
 */
@SpringBootApplication
@EnableEurekaServer
public class ShopEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopEurekaApplication.class, args);
    }
}
