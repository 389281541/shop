package com.rainbow.admin.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 实际数据源配置
 *
 * @author lujinwei
 * @since 2019-05-31
 */
@Component
@Data
@ConfigurationProperties(prefix = "hikari")
public class DBProperties {

    private HikariDataSource user;

}