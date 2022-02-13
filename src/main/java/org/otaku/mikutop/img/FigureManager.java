package org.otaku.mikutop.img;

import java.io.FileOutputStream;

/**
 * @author Tieria
 * @date 2022/2/8 15:33
 * @description: 管理加载进的形象
 * 为了下次启动时保留上一次用户选中的形象
 * 需要配置文件记录
 *
 */
public class FigureManager {

    //维护当前使用的形象
    private static String currentFigure = null;

    static {
        gainLastFigure();
    }

    public static String getCurrentFigure() {
        return currentFigure;
    }

    public static void setCurrentFigure(String currentFigure) {
        FigureManager.currentFigure = currentFigure;
    }


    private static void gainLastFigure() {
        if(FigureConfig.getConfig("current") != null) {
            currentFigure = (String)FigureConfig.getConfig("current");
        } else {
            try{
//                if(FigureConfig.getConfig("figure")) {
//                    currentFigure = ((String[]) FigureConfig.getConfig("figure"))[0];
//                } else if(FigureConfig.getConfig("figure") instanceof String) {
//                    currentFigure = (String) FigureConfig.getConfig("figure");
//                }
                currentFigure = FigureConfig.getConfig("figure");
            } catch (Exception e) {
                //日志+弹窗+报错+退出

            }
        }
    }

    /**
     * Constant类获取value的方法
     */
    public static String gainPath(String actionName, String extend) {
        // 动作路径
        if(null == extend) {
            return FigureConfig.getConfig("figure."+currentFigure+"."+actionName);
        } else {
            return FigureConfig.getConfig("figure."+currentFigure+"."+actionName+"."+extend);
        }
    }

}
