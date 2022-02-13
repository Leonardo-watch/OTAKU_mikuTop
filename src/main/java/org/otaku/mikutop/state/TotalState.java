package org.otaku.mikutop.state;

/**
 * @author Tieria
 * @date 2022/2/13 16:54
 * @description: 状态实体类
 */
public class TotalState {
    private static TotalState totalState;

    private final EmotionState emotionState;

    private TotalState() {
        emotionState = new EmotionState();
    }

    public static TotalState getInstance() {
        if (totalState == null) {
            totalState = new TotalState();
        }
        return totalState;
    }

    public EmotionState getEmotionState() {
        return emotionState;
    }

}
