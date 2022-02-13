package org.otaku.mikutop.constant;

import org.otaku.mikutop.img.FigureManager;
import org.otaku.mikutop.util.PathFileUtil;

/**
 * @author Tieria
 * @date 2022/2/7 23:06
 * @description: 各种常量，集中管理
 */
public class Constant {

    public static class ImageShow {
        /**
         * 主体的长和宽 可以添加复选框 选择是否能修改
         */
        public static int ImageHeight = 100;
        public static int ImageWidth = 100;

        //总前缀  URL适用前缀
        // 左斜杠 '/'
        public static final String RUNTIME_PATH = PathFileUtil.gainRuntimePath();

        //欢迎动作
        public static final String WELCOME = RUNTIME_PATH + FigureManager.gainPath("welcome", null);
        public static final String WELCOME_TEXT = FigureManager.gainPath("welcome", "text");
        public static final String WELCOME_VIDEO_PATH = null;

        //待机动作
        public static final String STANDBY = RUNTIME_PATH + FigureManager.gainPath("standby", null);
        public static final String STANDBY_TEXT =  FigureManager.gainPath("standby", "text");
        public static final String STANDBY_VIDEO_PATH = null;

        //随机动作
        public static final String RANDOM = RUNTIME_PATH + FigureManager.gainPath("random", null);
        public static final String RANDOM_TEXT =  FigureManager.gainPath("random", "text");
        public static final String RANDOM_VIDEO_PATH = null;

        //拖动动作z
        public static final String DRAGGING = RUNTIME_PATH + FigureManager.gainPath("dragging", null);
        public static final String DRAGGING_TEXT =  FigureManager.gainPath("dragging", "text");
        public static final String DRAGGING_VIDEO_PATH = null;

        //鼠标点击动作
        public static final String CLICK = RUNTIME_PATH + FigureManager.gainPath("click", null);
        public static final String CLICK_TEXT =  FigureManager.gainPath("click", "text");
        public static final String CLICK_VIDEO_PATH = null;

        //鼠标多次点击动作
        public static final String MULTIPLE_CLICKS = RUNTIME_PATH + FigureManager.gainPath("MultipleClicks", null);
        public static final String MULTIPLE_CLICK_TEXT =  FigureManager.gainPath("MultipleClicks", "text");
        public static final String MULTIPLE_CLICK_VIDEO_PATH = null;

        //饥饿动作
        public static final String EXIT = RUNTIME_PATH + FigureManager.gainPath("exit", null);
        public static final String EXIT_TEXT =  FigureManager.gainPath("exit", "text");
        public static final String EXIT_VIDEO_PATH = null;

        //饥饿动作
        public static final String HUNGRY = RUNTIME_PATH + FigureManager.gainPath("hungry", null);
        public static final String HUNGRY_TEXT =  FigureManager.gainPath("hungry", "text");
        public static final String HUNGRY_VIDEO_PATH = null;

        //人物切换动作
        public static final String SWITCH = RUNTIME_PATH + FigureManager.gainPath("switch", null);
        public static final String SWITCH_TEXT =  FigureManager.gainPath("switch", "text");
        public static final String SWITCH_VIDEO_PATH = null;
    }

    public static class UserInterface {
        /**
         * 交互时间，例如点击罗小黑会响应一个动作，该动作持续RunTime
         */
        public static final int ActionRunTime = 3;

        /**
         * 说话的消息框维持的时间
         */
        public static final int SayingRunTime = 4;

        /**
         * 碎碎念
         */

    }


}
