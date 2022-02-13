package org.otaku.mikutop.ui;

import org.otaku.mikutop.constant.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tieria
 * @date 2022/2/13 16:51
 * @description
 */
public class ActionGenerator {

    /**
     * 动作编号
     */
    private int actionIndex = NO_ACTION;

    private static final Map<Integer, String> RESOURCE = new HashMap<Integer, String>() {{
        put(1, Constant.ImageShow.CLICK);
    }};
    private static final int MIN_INDEX = 1;
    private static final int MAX_INDEX = 1;
    public static final int NO_ACTION = 0;

    /**
     * 随机生成一个动作编号，这里当动作编号不为0时说明动作还未结束
     *
     * @return 当且仅当上一个动作未结束时，返回false，且不生成新动作
     * 这个方法用来禁止重复点击 当上一个动作未结束时不进行下一个动作
     * 现在只用在了点击交互上 后期还可以用在其他交互上
     */
    public boolean generateNewActionIndex() {
        if (actionIndex != NO_ACTION) {
            return false;
        }
        actionIndex = (int) (Math.random() * (MAX_INDEX - MIN_INDEX + 1) + MIN_INDEX);
        return true;
    }

    /**
     * 结束动作时必须调用该API，约定的
     * 让actionIndex的状态变成NoAction
     * 也就是让现在的动作执行状态转为无动作执行 （不算待机动作）
     */
    public void close() {
        actionIndex = NO_ACTION;
    }

    /**
     * 获得动作的GIF资源
     *
     * @return 动作GIF资源文件相对路径
     */
    public String getActionPath() {
        if (RESOURCE.containsKey(actionIndex)){
            return RESOURCE.get(actionIndex);
        }
        return null;
    }
}
