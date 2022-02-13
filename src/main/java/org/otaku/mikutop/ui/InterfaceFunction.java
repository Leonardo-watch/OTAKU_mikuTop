package org.otaku.mikutop.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.otaku.mikutop.constant.Constant;
import javafx.scene.control.Label;
import org.otaku.mikutop.util.PathFileUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * @author Tieria
 * @date 2022/2/13 16:57
 * @description
 */
public class InterfaceFunction {
    private static InterfaceFunction interfaceFunction;

    private final ImageView imageView = GlobalVariableMaintainer.getInstance().getImageView();

    private final ActionExecutor actionExecutor = ActionExecutor.getInstance();

    private final Stage stage = GlobalVariableMaintainer.getInstance().getStage();

    // VBox 继承自 Pane  用来显示小黑说的话
    // 气泡框
    private final VBox messageBox;

    //这是一个复选框
    private final CheckboxMenuItem randomAction = new CheckboxMenuItem("随机动作");

    private InterfaceFunction() {
        this.messageBox = new VBox();
        initMessage();
        say(Constant.ImageShow.WELCOME_TEXT, 8);
        // 开启随机事件
        RandomEvent randomEvent = new RandomEvent();
        //多线程开启随机时事件
        new Thread(randomEvent).start();
    }

    //单例模式
    public static InterfaceFunction getInstance() {
        if (interfaceFunction == null) {
            interfaceFunction = new InterfaceFunction();
        }
        return interfaceFunction;
    }

    /**
     * 初始化消息框
     * 气泡框
     */
    private void initMessage() {
        Label bubble = new Label();
        // 设置气泡的宽度。如果没有这句，就会根据内容多少来自适应宽度
        bubble.setPrefWidth(100);
        bubble.setWrapText(true);   // 自动换行
        bubble.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-background-radius: 8px;");
        bubble.setPadding(new Insets(7)); // 标签的内边距的宽度
        bubble.setFont(new javafx.scene.text.Font(14));
        bubble.setTextFill(javafx.scene.paint.Color.web("#000000"));

        // 分别设置三角形三个顶点的X和Y
        // 用来设置气泡框中的指向三角
        javafx.scene.shape.Polygon triangle = new Polygon(0.0, 0.0, 8.0, 10.0, 16.0, 0.0);
        triangle.setFill(new Color(1, 1, 1, 0.7));

        // VBox.setMargin(triangle, new Insets(0, 50, 0, 0));//设置三角形的位置，默认居中
        messageBox.getChildren().addAll(bubble, triangle);
        messageBox.setAlignment(Pos.BOTTOM_CENTER);
        messageBox.setStyle("-fx-background:transparent;");
        messageBox.setLayoutX(0);        // 设置相对于父容器的位置  也就是ArchorPane
        messageBox.setLayoutY(0);
        messageBox.setVisible(true);
    }

    /**
     * 退出
     */
    public void exit() {
        // 展示告别动画
        double time = 1.5;
        actionExecutor.execute(Action.creatTemporaryUninterruptibleAction(Constant.ImageShow.EXIT, time, Constant.ImageShow.STANDBY));
        // 要用Platform.runLater，不然会报错Not on FX application thread;
        Platform.runLater(() -> say(Constant.ImageShow.EXIT_TEXT, Constant.UserInterface.SayingRunTime));
        // 动画结束后执行退出
        new Timeline(new KeyFrame(
                Duration.seconds(time),
                ae -> System.exit(0)))
                .play();
    }

    /**
     * 说一句话
     *
     * @param msg      消息
     * @param duration 持续时间
     */
    public void say(String msg, int duration) {
        Label lbl = (Label) messageBox.getChildren().get(0);
        lbl.setText(msg);
        messageBox.setVisible(true);
        //设置气泡的显示时间
        new Timeline(new KeyFrame(
                Duration.seconds(duration),
                ae -> messageBox.setVisible(false)))
                .play();
    }

    /**
     * 添加系统托盘
     * 菜单项(打开)中文乱码的问题是编译器的锅,
     * 如果使用IDEA,需要在Run-Edit Configuration在LoginApplication中的VM Options中添加-Dfile.encoding=GBK
     * @param stage 舞台
     */
    public void setTray(Stage stage) {
        SystemTray tray = SystemTray.getSystemTray();
        //托盘图标
        BufferedImage image;
        try {
            // 为托盘添加一个右键弹出菜单
            PopupMenu popMenu = new PopupMenu();
            popMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));

            MenuItem itemShow = new MenuItem("显示");

            //方法引用
            itemShow.addActionListener(e -> Platform.runLater(stage::show));

            MenuItem itemHide = new MenuItem("隐藏");
            // 要先setImplicitExit(false)，否则stage.hide()会直接关闭stage
            // stage.hide()等同于stage.close()
            itemHide.addActionListener(e -> {
                Platform.setImplicitExit(false);
                Platform.runLater(stage::hide);
            });

            MenuItem itemExit = new MenuItem("退出");
            itemExit.addActionListener(e -> exit());

            //开启随机动作
            randomAction.setState(true);
            popMenu.add(randomAction);
            popMenu.addSeparator();
            popMenu.add(itemShow);
            popMenu.add(itemHide);
            popMenu.add(itemExit);
            image = ImageIO.read(Objects.requireNonNull(PathFileUtil.gainProgramPath(Constant.ImageShow.WELCOME)));
            TrayIcon trayIcon = new TrayIcon(image, "小黑", popMenu);
            trayIcon.setToolTip("小黑");
            trayIcon.setImageAutoSize(true);//自动调整图片大小。这步很重要，不然显示的是空白
            tray.add(trayIcon);
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public VBox getMessageBox() {
        return messageBox;
    }

    /**
     * 随机事件改进
     * 带动作和对话
     * 未完成
     */
    class RandomEvent implements Runnable {
        @Override
        public void run() {
            while (true) {
                Random rand = new Random();
                //随机发生自动事件，以下设置间隔为9~24秒。要注意这个时间间隔包含了动画播放的时间
                long time = (rand.nextInt(15) + 10) * 1000L;
                if (randomAction.getState()) {

                }
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
