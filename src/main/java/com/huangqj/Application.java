package com.huangqj;

import com.huangqj.generate.CodeGenerate;
import com.huangqj.generate.DataSourceConfig;
import com.huangqj.generate.GlobalProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        generate();
        SpringApplication.run(Application.class, args);
    }

    /**
     * 全局生成
     */
    public static void generate() {
        //全局配置（具体配置释意详见GlobalProperties）
        GlobalProperties globalProperties = new GlobalProperties();
        globalProperties.setTemplatePath("/template/default");
        globalProperties.setAuthor("huangqj");
        globalProperties.setDomainPackage("com.huangqj.domain");
        globalProperties.setBeanPackage("com.huangqj.bean");
        globalProperties.setDtoPackage("com.huangqj.dto");
        globalProperties.setMapperPackage("com.huangqj.mapper");
        globalProperties.setMapperXmlPath("mapper");
        globalProperties.setServicePackage("com.huangqj.service");
        globalProperties.setControllerPackage("com.huangqj.controller");
        globalProperties.setTableNames(new String[]{"tbl_banner"});
        //生成的实体移除前缀
        globalProperties.setPrefix(new String[]{"tbl_"});
        globalProperties.setOutputDir("");

        //数据库配置
        DataSourceConfig dbConfig = new DataSourceConfig();
        dbConfig.setDriverName("com.mysql.jdbc.Driver");
        dbConfig.setUrl("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8");
        dbConfig.setUsername("root");
        dbConfig.setPassword("123456");
        CodeGenerate codeGenerate = new CodeGenerate(globalProperties, dbConfig);
        //生成代码
        codeGenerate.execute();
    }
}
