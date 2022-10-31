package com.huangqj.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 通用工具类
 */
public class CommonUtils {
    /**
     * 清除字符串下划线
     *
     * @param
     * @return
     */
    public static String getNoUnderlineStr(String str){
        if(str.indexOf("_") != -1){
            String[] strArray = str.split("_");
            StringBuffer sb = new StringBuffer();
            boolean flag = false;
            for(String key : strArray){
                if(flag){
                    // 下划线后的首字母大写
                    sb.append(StringUtils.capitalize(key));
                }else{
                    flag=true;
                    sb.append(key);
                }
            }
            str = sb.toString();
        }
        return str;
    }

    /**
     * 去掉字符串指定的前缀
     *
     * @param str
     * @param prefix
     * @return
     */
    public static String removePrefix(String str, String[] prefix) {
        if (StringUtils.isEmpty(str)) {
            return "";
        } else {
            if (null != prefix) {
                String[] prefixArray = prefix;

                for(int i = 0; i < prefix.length; ++i) {
                    String pf = prefixArray[i];
                    if (str.toLowerCase().matches("^" + pf.toLowerCase() + ".*")) {
                        return str.substring(pf.length());
                    }
                }
            }

            return str;
        }
    }

    /**
     * 首字母小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirst(String s){
        if(Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
