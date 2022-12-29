package com.huangqj.generate;

/**
 * 全局配置
 */
public class GlobalProperties {
    /**
     * 文件编码
     */
    private String systemEncoding = "utf-8";
    /**
     * 模板路径
     */
    private String templatePath = "/template";
    /**
     * 文件输出路径（默认输出到当前项目目录）
     */
    private String outputDir;
    /**
     * domain包名
     */
    private String domainPackage = "com.huangqj.domain";
    /**
     * bean包名
     */
    private String beanPackage = "com.huangqj.bean";
    /**
     * dto包名
     */
    private String dtoPackage = "com.huangqj.dto";
    /**
     * 服务包名
     */
    private String servicePackage = "com.huangqj.service";
    /**
     * 控制层包名
     */
    private String controllerPackage = "com.huangqj.controller";
    /**
     * mapper包名
     */
    private String mapperPackage = "com.huangqj.mapper";
    /**
     * util包名
     */
    private String utilPackage = "com.huangqj.utils";
    /**
     * mapper xml路径
     */
    private String mapperXmlPath = "";
    /**
     * page路径
     */
    private String pagePath = "";
    /**
     * 表名
     */
    private String[] tableNames;
    /**
     * 表前缀
     */
    private String[] prefix = {"tbl_"};
    /**
     * 排除字段
     */
    private String excludeFields = "deleted,create_time,update_time,create_by,update_by";
    /**
     * 作者
     */
    private String author = "huangqj";

    public String getSystemEncoding() {
        return systemEncoding;
    }

    public void setSystemEncoding(String systemEncoding) {
        this.systemEncoding = systemEncoding;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getDomainPackage() {
        return domainPackage;
    }

    public void setDomainPackage(String domainPackage) {
        this.domainPackage = domainPackage;
    }

    public String getBeanPackage() {
        return beanPackage;
    }

    public void setBeanPackage(String beanPackage) {
        this.beanPackage = beanPackage;
    }

    public String getDtoPackage() {
        return dtoPackage;
    }

    public void setDtoPackage(String dtoPackage) {
        this.dtoPackage = dtoPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getUtilPackage() {
        return utilPackage;
    }

    public void setUtilPackage(String utilPackage) {
        this.utilPackage = utilPackage;
    }

    public String getMapperXmlPath() {
        return mapperXmlPath;
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public String[] getPrefix() {
        return prefix;
    }

    public void setPrefix(String[] prefix) {
        this.prefix = prefix;
    }

    public String getExcludeFields() {
        return excludeFields;
    }

    public void setExcludeFields(String excludeFields) {
        this.excludeFields = excludeFields;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
