package com.xinlin.code;


import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class DemoTest {

    @Test
    public void test() throws Exception{
//        String entityName = data.get("entity").toString();
        Map<String,Object> data = new HashMap();
//        data.put("dataList",getData());
//        data.put("dataList",getModeData());
        data.put("dataList",getResaleData());
        String fileNamePath = getProjectPath()+"src/main/resources/code/model.java";//获取生成的文件路径
        System.out.println("fileNamePath:"+fileNamePath);
        String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
        Template template = getConfiguration().getTemplate("entityTemplate.ftl");//获取模版信息
        FileUtils.forceMkdir(new File(fileDir + "/"));
        Writer out = new OutputStreamWriter(
                new FileOutputStream(fileNamePath), "utf-8");//生成的文件编码
        template.process(data, out);//结合模版生成代码文件
        out.close();
    }

    public class RowVo{
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public List<RowVo> getData(){
        Map<String, String> headMap = new LinkedHashMap();
        headMap.put("num", "序号");
        headMap.put("assetSystemNo", "系统编号");
        headMap.put("assetCode", "资产编号");
        headMap.put("assetSerialNo", "资产序列号");
        headMap.put("fuzu", "辅组");
        headMap.put("assetName", "资产名称");
        headMap.put("assetInventoryNo", "存货号");
        headMap.put("secondaryType", "资产类型");
        headMap.put("assetCompany", "归属公司");
        headMap.put("assetCompanyCode", "归属公司编码");
        headMap.put("assetDepartment", "归属部门");
        headMap.put("assetDepartmentCode", "归属部门编号");
        headMap.put("assetBrandModel", "品牌型号");
        headMap.put("sapScrapTime", "报废日期");
        headMap.put("assetStatus", "资产状态");
        headMap.put("usageMonth", "使用时长");
        headMap.put("userEmpCode", "使用人工号");
        headMap.put("userEmpName", "使用人姓名");
        headMap.put("userDepartment", "使用人部门");
        headMap.put("userMobileNo", "使用人电话");
        headMap.put("workplaceName", "使用地点");
        headMap.put("storageId", "仓库编号");
        headMap.put("storageName", "仓库名称");
        headMap.put("createTime", "最后更新时间");
        headMap.put("operationType", "操作类型");
        headMap.put("operationDetails", "处理内容");
        headMap.put("operatorEmpCode", "操作人编号");
        headMap.put("operatorEmpName", "操作人");
        headMap.put("operateTime", "操作时间");

        List<RowVo> list = new ArrayList<RowVo>();
        Iterator<Map.Entry<String,String>> entries = headMap.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String,String> entry = entries.next();
            RowVo vo = new RowVo();
            vo.setName(entry.getKey());
            vo.setValue(entry.getValue());
            list.add(vo);
        }
        return list;

    }


    public Configuration getConfiguration()
            throws IOException
    {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(super.getClass(), "/template/other");
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
    public static String getProjectPath()
    {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

     public List<RowVo> getModeData(){
         Map<String, String> headMap = new LinkedHashMap();
         headMap.put("num", "序号");
         headMap.put("moveDate", "异动时间");
         headMap.put("assetCompanyCode", "公司");
         headMap.put("moveType", "异动类型");
         headMap.put("assetCode", "资产编号");
         headMap.put("assetName", "资产名称");
         headMap.put("assetBrandModel", "型号");
         headMap.put("count", "数量");
         headMap.put("hisStorageLocation", "异动前存放位置");
         headMap.put("hisAssetDepartmentCode", "异动前使用部门编号");
         headMap.put("hisAssetDepartment", "异动前使用部门");
         headMap.put("hisUserEmpName", "异动前使用人");
         headMap.put("hisUserEmpCode", "异动前使用人编号");
         headMap.put("newStorageLocation", "异动后存放位置");
         headMap.put("newAssetDepartmentCode", "异动后使用部门编号");
         headMap.put("newAssetDepartment", "异动后使用部门");
         headMap.put("newUserEmpName", "异动后使用人");
         headMap.put("newUserEmpCode", "异动后使用人编号");

         List<RowVo> list = new ArrayList<RowVo>();
         Iterator<Map.Entry<String,String>> entries = headMap.entrySet().iterator();
         while(entries.hasNext()){
             Map.Entry<String,String> entry = entries.next();
             RowVo vo = new RowVo();
             vo.setName(entry.getKey());
             vo.setValue(entry.getValue());
             list.add(vo);
         }
         return list;
     }

    public List<RowVo> getResaleData(){
        Map<String, String> headMap = new LinkedHashMap();
        headMap.put("num", "序号");
        headMap.put("resaleDate", "转售时间");
        headMap.put("applyCode", "申请编码");
        headMap.put("contractNum", "合同号");
        headMap.put("hisAssetCompanyCode", "旧公司");
        headMap.put("hisAssetCode", "资产编码");
        headMap.put("secondaryTypeCode", "类型");
        headMap.put("assetBrandModel", "型号");
        headMap.put("count", "数量");
        headMap.put("originalVal", "原值");
        headMap.put("netVal", "净值");
        headMap.put("totalAmount", "含税总价");
        headMap.put("newAssetCompanyCode", "新公司");
        headMap.put("newAssetCode", "新编码");
        headMap.put("capitalizationDate", "资本化日期");
        headMap.put("userDepartmentCode", "使用部门编码");
        headMap.put("userDepartment", "使用部门");
        headMap.put("userEmpCode", "实际使用人编码");
        headMap.put("userEmpName", "姓名");
        headMap.put("workplaceAddress", "位置");

        List<RowVo> list = new ArrayList<RowVo>();
        Iterator<Map.Entry<String,String>> entries = headMap.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String,String> entry = entries.next();
            RowVo vo = new RowVo();
            vo.setName(entry.getKey());
            vo.setValue(entry.getValue());
            list.add(vo);
        }
        return list;
    }
}
