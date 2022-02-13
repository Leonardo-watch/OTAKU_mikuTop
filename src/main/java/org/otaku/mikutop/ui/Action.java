package org.otaku.mikutop.ui;

/**
 * @author Tieria
 * @date 2022/2/13 16:43
 * @description: 动作的抽象定义
 */
public class Action {

    /**
     * 当前动作
     */
    private final String path;

    /**
     * 动作维持时间，如果为-1则保持该动作
     */
    private final double time;

    /**
     * 是否是暂时的动作
     */
    private final boolean isTemporaryAction;

    /**
     * 如果是暂时的动作，则应当在该时间内恢复成这个动作(recoverPath)
     */
    private String recoverPath;

    /**
     * 是否可中断
     */
    private final boolean interruptable;

    /**
     * 若动作是持续的，则维持时间为 PERPETUAL_TIME
     */
    public static final double PERPETUAL_TIME = -1.0;

    //
    private Action(String path, double time, boolean isTemporaryAction, String recoverPath, boolean interruptable) {
        this.path = path;
        this.time = time;
        this.isTemporaryAction = isTemporaryAction;
        this.recoverPath = recoverPath;
        this.interruptable = interruptable;
    }

    private Action(String path, double time, boolean isTemporaryAction, boolean interruptable) {
        this.path = path;
        this.time = time;
        this.isTemporaryAction = isTemporaryAction;
        this.interruptable = interruptable;
    }

    /**
     * 创建暂时的、可中断的动作
     *
     * @param path        动作路径
     * @param time        持续时间
     * @param recoverPath 恢复动作路径
     * @return 创建的动作实例
     */
    public static Action creatTemporaryInterruptableAction(String path, double time, String recoverPath) {
        return new Action(path, time, true, recoverPath, true);
    }

    /**
     * 创建持续的、可中断的动作
     * 持续动作 设置的值为 -1
     * @param path 动作路径
     * @return 创建的动作实例
     */
    public static Action creatContinuousInterruptableAction(String path) {
        return new Action(path, PERPETUAL_TIME, false, true);
    }

    /**
     * 创建短暂的、不可中断的动作，例如退出动画
     *
     * @param path        动作路径
     * @param time        持续时间
     * @param recoverPath 恢复动作路径
     * @return 创建的动作实例
     */
    public static Action creatTemporaryUninterruptibleAction(String path, double time, String recoverPath) {
        return new Action(path, time, true, recoverPath, false);
    }

    /**
     * 创建持续的、不可中断的动作，比较苛刻展示想不到案例
     *
     * @param path 动作路径
     * @return 创建的动作实例
     */
    public static Action creatContinuousUninterruptibleAction(String path) {
        return new Action(path, PERPETUAL_TIME, false, false);
    }

    public String getPath() {
        return path;
    }

    public double getTime() {
        return time;
    }

    public boolean isTemporaryAction() {
        return isTemporaryAction;
    }

    public String getRecoverPath() {
        return recoverPath;
    }

    public boolean isInterruptable() {
        return interruptable;
    }
}
