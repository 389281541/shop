package com.rainbow.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 配置中心启动类
 *
 * @author lujinwei
 * @Date 2019-05-21 15:15
 */
@SpringBootApplication
@EnableConfigServer
public class ShopConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopConfigApplication.class);
    }
}
