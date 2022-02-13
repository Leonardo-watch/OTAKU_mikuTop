package org.otaku.mikutop.util;

import cn.hutool.core.io.FileUtil;
import org.otaku.mikutop.MikuApplication;
import org.otaku.mikutop.constant.Constant;
import org.otaku.mikutop.constant.RuntimeEnvEnum;
import org.otaku.mikutop.ui.GlobalVariableMaintainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tieria
 * @date 2022/2/8 16:15
 * @description: 路径工具类
 *
 *
 */
public class PathFileUtil {


    public static String gainRuntimePath() {
        if(GlobalVariableMaintainer.getInstance().getRuntimeEnv() == RuntimeEnvEnum.DEVELOPMENT) {
            return ClassLoader.getSystemResource("").getPath().substring(1);
        } else {
            return System.getProperty("exe.path").replace('\\', '/');
        }
    }

    /**
     * 获取项目目录路径  用来扫properties
     * @return
     */
    public static String gainProgramPath()  {
        try {
            if(GlobalVariableMaintainer.getInstance().getRuntimeEnv() == RuntimeEnvEnum.DEVELOPMENT) {
                return new File("").getCanonicalPath();
            } else {
                return System.getProperty("exe.path");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 扫包并拿到目录下的配置文件  .properties
     *
     */
    public static List<String> scanProperties()  {
        File file = new File(Objects.requireNonNull(PathFileUtil.gainProgramPath()));
        List<String> list = new ArrayList<>();
        if(null == file) {

        }
        File[] files = file.listFiles();
        for (File file1 : files) {
            //获取后缀名为Properties的配置文件
            if("properties".equals(file1.getName().substring(file1.getName().lastIndexOf(".") + 1))) {
                list.add(file1.getName());
            }
        }
        //需要检查是否有figure.properties文件
        checkFigure(list);
        return list;
    }

    private static void checkFigure(List<String> lists) {
        if(!checkFigureProp(lists)) {
            // 日志+弹窗+报错+退出
            System.out.println("未检测到figure.properties文件");
        }
    }

    private static boolean checkFigureProp(List<String> lists) {
        for (String str: lists) {
            if("figure.properties".equals(str)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }


    /**
     * 根据spec 获取文件的InputStream
     * URI的str 的格式 为 file：E：/....
     *                     |     |
     *                协议名   路径（左斜杠）
     * @param spec
     * @return
     */
    public static InputStream gainProgramPath(String spec) {
        try {
            try {
                return new URI(  "file:"+ spec).toURL().openStream();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
