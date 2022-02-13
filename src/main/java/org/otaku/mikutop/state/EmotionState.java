package org.otaku.mikutop.state;

import javafx.scene.image.ImageView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.otaku.mikutop.MikuApplication;
import org.otaku.mikutop.img.ResourceGetter;

/**
 * @author Tieria
 * @date 2022/2/13 16:55
 * @description: 情绪值
 */
public class EmotionState {
    /**
     * 心情值，取值为[0, 100]
     */
    private int EMOTION = 60;
    public static final int REDUCE_STEP = 5;
    public static final int INCREASE_STEP = 10;
    public static final int MAX_VALUE = 100;
    public static final int MIN_VALUE = 0;
    private final ImageView imageView;

    private static final Log LOG = LogFactory.getLog(EmotionState.class);

    public EmotionState() {
        imageView = new ImageView();
    }

    /**
     * 心情值降低
     */
    public void reduce() {
        EMOTION = Math.max(MIN_VALUE, EMOTION - REDUCE_STEP);
    }

    /**
     * 心情值增加
     */
    public void increase() {
        if (EMOTION < MAX_VALUE) {
            showIncreasedAnimation();
        }
        EMOTION = Math.min(MAX_VALUE, EMOTION + INCREASE_STEP);
        LOG.info("[EmotionState::increase]-当前心情 = " + EMOTION);
    }

    /**
     * 展示心情增加的动画
     */
    private void showIncreasedAnimation() {
//        Image increasingImg = resourceGetter.get(Constant.ImageShow.emotionIncreasingImage);
//        imageView.setImage(increasingImg);
//        imageView.setStyle("-fx-background:transparent;");
//        // 设置相对于父容器的位置
//        //这里设置的是云的动效
//        imageView.setX(0);
//        imageView.setY(0);
//        imageView.setLayoutX(60);
//        imageView.setLayoutY(0);
//        imageView.setFitHeight(80);         // 设置图片显示的大小
//        imageView.setFitHeight(80);
//        imageView.setPreserveRatio(true);   // 保留width:height比例
//        imageView.setVisible(true);
//
//        double millis = Constant.UserInterface.ActionRunTime * 1000;
//        // 位移动画
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(millis), imageView);
//        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
//        translateTransition.setFromY(40);
//        translateTransition.setToY(0);
//        // translateTransition.play();
//        // 淡入淡出动画
//        FadeTransition fadeTransition = new FadeTransition(Duration.millis(millis), imageView);
//        fadeTransition.setFromValue(1.0);
//        fadeTransition.setToValue(0);
//        // 并行执行动画
//        ParallelTransition parallelTransition = new ParallelTransition();
//        parallelTransition.getChildren().addAll(
//                fadeTransition,
//                translateTransition
//        );
//        parallelTransition.play();
    }

    public ImageView getImageView() {
        return imageView;
    }
}
