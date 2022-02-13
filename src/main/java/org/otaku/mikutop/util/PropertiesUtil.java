package org.otaku.mikutop.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Tieria
 * @date 2022/2/11 22:17
 * @description: 配置文件工具类
 */
public class PropertiesUtil {

    /**
     * 通过 URL 读取 .properties 配置文件
     * @param propertiesUrl 配置文件的路径
     * @return 配置文件中的key-value值
     */
    public static Map<String, String> getProperties(String propertiesUrl) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Properties prop = new Properties();
        try {
            // 读取属性文件a.properties
            InputStreamReader in = new InputStreamReader(new FileInputStream(propertiesUrl), "UTF-8");
            // 加载属性列表
            prop.load(in);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                resultMap.put(key, prop.getProperty(key));
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultMap;
    }


}
