package com.huangqj.generate;

import com.huangqj.utils.CommonUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成器
 */
public class CodeGenerate {
    private static Logger log = LoggerFactory.getLogger(CodeGenerate.class);
    /**
     * 全局配置
     */
    private GlobalProperties globalProperties;
    /**
     * 数据库配置
     */
    private DataSourceConfig dataSourceConfig;

    public CodeGenerate(GlobalProperties globalProperties, DataSourceConfig dataSourceConfig) {
        this.globalProperties = globalProperties;
        this.dataSourceConfig = dataSourceConfig;
    }

    /**
     * 代码生成执行
     *
     * @return
     */
    public void execute() {
        List<Table> tableList = getTableList();
        if(CollectionUtils.isEmpty(tableList)) {
            return;
        }
        Map<String, Object> data = getGlobalPropertiesMap();
        for(Table table : tableList){
            try{
                String domain = CommonUtils.getNoUnderlineStr(CommonUtils.removePrefix(table.getName().toLowerCase(), globalProperties.getPrefix()));
                data.put("domain", StringUtils.capitalize(domain));
                data.put("table", table);
                generateFile("domainTemplate.ftl", "domain", data);
                generateFile("beanTemplate.ftl", "bean", data);
                generateFile("dtoTemplate.ftl", "dto", data);
                generateFile("mapperTemplate.ftl", "mapper", data);
                generateFile("serviceTemplate.ftl", "service", data);
                generateFile("serviceImplTemplate.ftl", "serviceImpl", data);
                generateFile("controllerTemplate.ftl", "controller", data);
                if (StringUtils.isNotBlank(globalProperties.getMapperXmlPath())){
                    generateFile("mapperXmlTemplate.ftl", "mapperXml", data);
                }
                generateFile("listTemplate.ftl", "list", data);
                generateFile("addTemplate.ftl", "add", data);
                generateFile("editTemplate.ftl", "edit", data);
                generateFile("detailTemplate.ftl", "detail", data);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 把配置数据注入模版，生成代码文件
     *
     * @param templateFileName
     * @param type
     * @param data
     */
    public void generateFile(String templateFileName, String type, Map<String, Object> data) throws Exception {
        String filePath = getCodePath(type, data.get("domain").toString());
        Template template = getTemplateConfiguration().getTemplate(templateFileName);
        File file = new File((filePath));
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Writer out = new OutputStreamWriter(
                new FileOutputStream(filePath), globalProperties.getSystemEncoding());
        template.process(data, out);
        out.close();
    }

    /**
     * 获取freemarker模版配置
     *
     * @return
     * @throws Exception
     */
    public Configuration getTemplateConfiguration() throws Exception {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(super.getClass(), globalProperties.getTemplatePath());
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding(globalProperties.getSystemEncoding());
        return cfg;
    }

    /**
     * 获取表列表
     *
     */
    private List<Table> getTableList(){
      if(dataSourceConfig == null){
          throw new RuntimeException("dataSourceConfig is null");
      }
      if(globalProperties == null){
          throw new RuntimeException("globalConfig is null");
      }
      return dataSourceConfig.getTableList(globalProperties.getTableNames());
    }

    /**
     * 获取代码生成的文件路径
     *
     * @param type
     * @param domain
     * @return
     */
    public String getCodePath(String type, String domain) {
        StringBuilder path = new StringBuilder();
        if (StringUtils.isNotBlank(type)) {
            // 项目路径
            if(StringUtils.isEmpty(this.globalProperties.getOutputDir())) {
                String projectPath = System.getProperty("user.dir");
                path.append(projectPath + "/src/main");
            } else {
                path.append(this.globalProperties.getOutputDir());
            }
            if("domain".equals(type)) {
                path.append("/java/").append(globalProperties.getDomainPackage().replaceAll("\\.", "\\/")).append("/");
                path.append(domain).append(".java");
            } else if("bean".equals(type)) {
                path.append("/java/").append(globalProperties.getBeanPackage().replaceAll("\\.", "\\/")).append("/");
                path.append(domain).append("Bean").append(".java");
            } else if("dto".equals(type)) {
                path.append("/java/").append(globalProperties.getDtoPackage().replaceAll("\\.", "\\/")).append("/");
                path.append(domain).append("DTO").append(".java");
            } else if("mapper".equals(type)) {
                path.append("/java/").append(globalProperties.getMapperPackage().replaceAll("\\.", "\\/")).append("/");
                path.append(domain).append("Mapper").append(".java");
            } else if("mapperXml".equals(type)) {
                path.append("/resources/").append(globalProperties.getMapperXmlPath().replaceAll("\\.", "\\/"));
                path.append("/");
                path.append(domain).append("Mapper").append(".xml");
            } else if("service".equals(type)) {
                path.append("/java/").append(globalProperties.getServicePackage().replaceAll("\\.", "\\/")).append("/");
                path.append(domain).append("Service").append(".java");
            } else if("serviceImpl".equals(type)) {
                path.append("/java/").append(globalProperties.getServicePackage().replaceAll("\\.", "\\/")).append("/impl/");
                path.append(domain).append("ServiceImpl").append(".java");
            } else if("controller".equals(type)) {
                path.append("/java/").append(globalProperties.getControllerPackage().replaceAll("\\.", "\\/")).append("/");
                path.append(domain).append("Controller").append(".java");
            } else if("list".equals(type) || "add".equals(type) || "edit".equals(type) || "detail".equals(type)) {
                path.append("/resources/template").append(globalProperties.getPagePath().replaceAll("\\.", "\\/")).append("/");
                path.append(CommonUtils.toLowerCaseFirst(domain));
                path.append("/");
                path.append(type).append(".ftl");
            } else{
                throw new IllegalArgumentException("type is illegal");
            }

        } else {
            throw new IllegalArgumentException("type is null");
        }
        return path.toString();
    }

    /**
     * 获取全局配置map
     *
     * @return
     */
    public Map<String, Object> getGlobalPropertiesMap() {
        Map<String, Object> data = new HashMap();
        data.put("domainPackage", globalProperties.getDomainPackage());
        data.put("beanPackage", globalProperties.getBeanPackage());
        data.put("dtoPackage", globalProperties.getDtoPackage());
        data.put("controllerPackage", globalProperties.getControllerPackage());
        data.put("servicePackage", globalProperties.getServicePackage());
        data.put("mapperPackage",globalProperties.getMapperPackage());
        data.put("author", globalProperties.getAuthor());
        data.put("date",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return data;
    }
}
