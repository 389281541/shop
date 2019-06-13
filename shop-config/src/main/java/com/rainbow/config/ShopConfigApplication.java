package com.rainbow.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心启动类
 *
 * @author: lujinwei
 * @since: 2018-12-17
 */
@SpringBootApplication
@EnableConfigServer
public class ShopConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopConfigApplication.class);
    }
}
