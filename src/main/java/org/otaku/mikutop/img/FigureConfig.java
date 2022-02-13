package org.otaku.mikutop.img;

import org.otaku.mikutop.util.PathFileUtil;
import org.otaku.mikutop.util.PropertiesUtil;

import java.io.*;
import java.util.*;

/**
 * @author Tieria
 * @date 2022/2/8 15:31
 * @description: 获得不同形象的常量
 *
 * 读取配置文件
 */
public class FigureConfig {

    private static Properties properties;

    static {
        init();
    }

    private static void init() {
        properties= new Properties();
        List<String> propertiesNames = PathFileUtil.scanProperties();
        try {
            for (String propertiesName : propertiesNames) {
                InputStreamReader in = new InputStreamReader(new FileInputStream(propertiesName), "UTF-8");
                // 加载属性列表
                properties.load(in);
                in.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * String
     * @param configName
     * @return
     */
    public static String getConfig(String configName) {
        return getConfig(configName, null);
    }

    public static String getConfig(String configName, String defaultValue) {
        String property = properties.getProperty(configName);
        if("".equals(property)) {
            // 日志 Warning  configName 未配置
        }
        return !"".equals(property) ? property : defaultValue;
    }


    /**
     * 将配置文件中 用英文 ',' 隔开的配置分开 成String[]
     * 未使用
     * @param str
     * @return
     */
    private static Object gainKeyValue(String str) {
        if(str.contains(",")) {
            String[] strings = str.split(",");
            return strings;
        } else {
            return str;
        }
    }

    /**
     * 在窗口关闭的时候调用这句
     */
    public static void storeUserSetting() {
        try {
            Properties properties1 = new Properties();
            FileOutputStream fos = new FileOutputStream("userSetting.properties");
            properties1.setProperty("current", FigureManager.getCurrentFigure());
            properties1.store(fos, "存入用户使用的形象");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
