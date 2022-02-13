package org.otaku.mikutop.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.otaku.mikutop.constant.Constant;
import org.otaku.mikutop.img.ResourceGetter;
import org.otaku.mikutop.state.TotalState;

/**
 * @author Tieria
 * @date 2022/2/13 16:50
 * @description: 动作执行者
 */
public class ActionExecutor {


    //从GlobalVariableMaintainer中拿到的ImageView单例对象
    private final ImageView imageView = GlobalVariableMaintainer.getInstance().getImageView();
    //
    private Action curAction;
    //资源获取器
    private final ResourceGetter resourceGetter = ResourceGetter.getInstance();
    //
    private final ActionGenerator actionGenerator = new ActionGenerator();
    //自身实例对象
    private static ActionExecutor actionExecutor;
    /**
     * timeline 时间轴
     */
    private Timeline timeline;

    public static ActionExecutor getInstance() {
        if (actionExecutor == null) {
            actionExecutor = new ActionExecutor();
        }
        return actionExecutor;
    }

    private ActionExecutor() {
    }

    /**
     * 动作执行方法
     *
     * @param action
     * @return
     */
    public boolean execute(Action action) {
        if (curAction != null && !curAction.isInterruptable()) {return false;}
        Image actionImage = resourceGetter.get(action.getPath());
        imageView.setImage(actionImage);
        curAction = action;
        if (timeline != null) {
            timeline.pause();
        }
        // 如果当前动作是暂时的，则还需要恢复到某一个动作
        if (action.isTemporaryAction()) {
            //timeline 延迟执行
            timeline = new Timeline(new KeyFrame(
                    Duration.seconds(action.getTime()),
                    e -> executeContinuousInterruptableActionAction(action.getRecoverPath())
            ));
            timeline.play();
        }
        return true;
    }

    /**
     * 点击动作执行方法
     * @return
     */
    public boolean executeClickAction() {
        boolean ok = actionGenerator.generateNewActionIndex();
        if (ok) {
            execute(Action.creatTemporaryInterruptableAction(
                    actionGenerator.getActionPath(),
                    Constant.UserInterface.ActionRunTime,
                    Constant.ImageShow.STANDBY));
            // 同时会增加心情值
            TotalState.getInstance().getEmotionState().increase();
        }
        return ok;
    }

    /**
     * 立即执行一个可中断的、持续的动作
     */
    private void executeContinuousInterruptableActionAction(String path) {
        curAction = null;
        timeline = null;
        actionGenerator.close();
        Action action = Action.creatContinuousInterruptableAction(path);
        execute(action);
    }

}
