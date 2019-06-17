package com.rainbow.admin.config;


import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 数据源抽象类
 *
 * @author lujinwei
 * @since 2019-05-31
 */
public abstract class AbstractDataSource {

    protected DataSource dataSource(HikariDataSource source) {
        /**
         * MySql数据库驱动 实现 XADataSource接口
         */
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(source.getJdbcUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(source.getPassword());
        mysqlXaDataSource.setUser(source.getUsername());
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(source.getPoolName());
        // xaDataSource.setMinPoolSize(source.getMinPoolSize());
        xaDataSource.setMaxPoolSize(source.getMaximumPoolSize());
        xaDataSource.setMaxLifetime((int) source.getMaxLifetime());
        // xaDataSource.setLoginTimeout(source.getLoginTimeout());
        xaDataSource.setMaxIdleTime((int) source.getIdleTimeout());
        xaDataSource.setTestQuery("SELECT 1");
        return xaDataSource;
    }


    protected SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        return bean.getObject();
    }

    protected SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}


