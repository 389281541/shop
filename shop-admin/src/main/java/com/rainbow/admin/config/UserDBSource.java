package com.rainbow.admin.config;

import com.rainbow.common.constant.Constant;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by zsw on 2018/7/16.
 */
@Configuration
@MapperScan(basePackages = "com.rainbow.admin.mapper.user", sqlSessionTemplateRef = Constant.DATASOURCE_USER + "SqlSessionTemplate")
public class UserDBSource extends AbstractDataSource {

    @Autowired
    private DBProperties properties;

    private final static String DATASOURCE = Constant.DATASOURCE_USER;


    @Bean(name = DATASOURCE)
    public DataSource userDataSource() {
        HikariDataSource source = properties.getUser();
        return dataSource(source);
    }

    @Bean(name = DATASOURCE + "SqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier(DATASOURCE) DataSource dataSource) throws Exception {
        return sqlSessionFactory(dataSource);
    }

    @Bean(name = DATASOURCE + "SqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier(DATASOURCE + "SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return sqlSessionTemplate(sqlSessionFactory);
    }


}
