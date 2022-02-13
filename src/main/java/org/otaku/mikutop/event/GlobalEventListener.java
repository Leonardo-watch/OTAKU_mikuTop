package org.otaku.mikutop.event;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.otaku.mikutop.MikuApplication;
import org.otaku.mikutop.ui.ActionExecutor;

import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.input.MouseButton.SECONDARY;

/**
 * @author Tieria
 * @date 2022/2/13 17:05
 * @description: 全局事件监听者
 */
public class GlobalEventListener {


    private static final Log LOG = LogFactory.getLog(GlobalEventListener.class);
    private final Stage stage;
    private final ImageView imageView;
    private final AnchorPane anchorPane;
    /**
     * 动作执行者，触发的动作需要托付给动作执行者执行
     */
    private final ActionExecutor actionExecutor;

    private double xOffset = 0;
    private double yOffset = 0;

    private double preScreenX = 0;
    private double preScreenY = 0;

    /**
     * @param stage
     * @param imageView
     * @param anchorPane
     */
    public GlobalEventListener(Stage stage, ImageView imageView, AnchorPane anchorPane) {
        this.stage = stage;
        this.imageView = imageView;
        this.anchorPane = anchorPane;
        this.actionExecutor = ActionExecutor.getInstance();
        enableDrag();
        enableClick();
        LOG.info("启动全局事件监听器");
//        enableContextMenu();
    }

    /**
     * 激活拖动
     */
    private void enableDrag() {
        anchorPane.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        // 拖动事件
        anchorPane.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
    }

    /**
     * 点击触发一个动作
     */
    private void enableClick() {
        imageView.setOnMousePressed(e -> {
            if (e.getButton() == PRIMARY) {
                preScreenX = e.getScreenX();
                preScreenY = e.getScreenY();
            }
        });
        imageView.setOnMouseReleased(e -> {
            if (e.getButton() == PRIMARY) {
                // 判断是点击后释放不是拖动后释放
                if (e.getScreenX() == preScreenX && e.getScreenY() == preScreenY) {
                    actionExecutor.executeClickAction();
                }
            }
        });
    }

//    private void enableContextMenu() {
//        imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
//            if (e.getButton() == SECONDARY) {
//                // e.getPickResult().getIntersectedNode();是啥意思..?
//                Node node = e.getPickResult().getIntersectedNode();
//                // 给node对象添加下来菜单
//                ContextMenu.getInstance().show(node, e.getScreenX(), e.getScreenY());
//            }
//        });
//    }

}
