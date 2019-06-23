package com.rainbow.admin;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis自动生成类
 */
public class MpGenerator {

    private static String entityVM = "/templates/entity.java.vm";
    private static String controllerVM = "/templates/controller.java.vm";
    private static String serviceVM = "/templates/service.java.vm";
    private static String serviceImplVM = "/templates/serviceImpl.java.vm";
    private static String xmlVM = "/templates/mapper.xml.vm";
    @Test
    public void generateCode() {
        String packageName = "com.rainbow.admin";
        boolean serviceNameStartWithI = true;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, packageName, "user");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/vvshop_user";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("mysql")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)           //开启全局大写命名
                .setEntityColumnConstant(true)
                .setTablePrefix("")             //生成表注解
                .setEntityColumnConstant(false)
                .setVersionFieldName("")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .entityTableFieldAnnotationEnable(true)   //生成字段注解
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityBuilderModel(false)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        config.setActiveRecord(true)
                .setAuthor("lujinwei")
                .setOutputDir("src/main/java")
                .setSwagger2(true)
                .setEnableCache(false)
//                .setBaseResultMap(true)
//                .setBaseColumnList(true)
                .setFileOverride(true);//生成基本的SQL片段 ;

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 调整 xml 生成目录演示
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig(xmlVM) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return  "src/main/resoures/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        cfg.setFileOutConfigList(focList);

        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setService("service")
                                .setEntity("entity")
                                .setServiceImpl("service.impl")
                )
                .setCfg(cfg)
                .setTemplate(
                        new TemplateConfig().setXml(null)
                                            .setEntity(entityVM)
                                            .setController(controllerVM)
                                            .setService(serviceVM)
                                            .setServiceImpl(serviceImplVM)
                            )
                .execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
