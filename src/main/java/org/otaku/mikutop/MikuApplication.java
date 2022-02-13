package org.otaku.mikutop;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.otaku.mikutop.constant.Constant;
import org.otaku.mikutop.event.GlobalEventListener;
import org.otaku.mikutop.exception.WarningWindow;
import org.otaku.mikutop.img.FigureConfig;
import org.otaku.mikutop.img.FigureManager;
import org.otaku.mikutop.img.ResourceGetter;
import org.otaku.mikutop.state.TotalState;
import org.otaku.mikutop.ui.GlobalVariableMaintainer;
import org.otaku.mikutop.ui.InterfaceFunction;


/**
 * @author Tieria
 * @date 2022/2/7
 * @description: 启动类
 */
public class MikuApplication extends Application {

    //确保全局变量控制器在第一位
    private final GlobalVariableMaintainer GLOBAL_VAR = GlobalVariableMaintainer.getInstance();
    private static final Log LOG = LogFactory.getLog(MikuApplication.class);
    private final ImageView imageView = GlobalVariableMaintainer.getInstance().getImageView();
    private final Stage stage = GlobalVariableMaintainer.getInstance().getStage();
    private AnchorPane pane;
    private final ResourceGetter resourceGetter = ResourceGetter.getInstance();
    private final InterfaceFunction interfaceFunction = InterfaceFunction.getInstance();
    private final TotalState totalState = TotalState.getInstance();
    private GlobalEventListener globalEventListener;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 开发环境下 主路径为  PathFileUtil.gainProgramPath() --- classpath  也就是target中的clssses路径
        // 发布环境下 目录为 System.getProperty("exe.path")   --- exe父目录路径

        //====设置任务栏不出现程序图标====
        // UTILITY 风格不会出现任务栏程序图标
        // javaFX按道理应该是所有顶级窗口都会出现程序图标
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0);
        stage.initOwner(primaryStage);
        LOG.info("开启窗口");
        InitImageView();
        pane = new AnchorPane(
                interfaceFunction.getMessageBox(),
                imageView
        );
        pane.setStyle("-fx-background:transparent;");
        globalEventListener = new GlobalEventListener(stage, imageView, pane);

        InitStage(stage);

        primaryStage.show();

        stage.show();

        //添加系统托盘 弹窗的托盘应该在interfaceFunction中取到
        interfaceFunction.setTray(stage);
    }

    // 设置父类
    // 设置长宽、设置偏移长宽
    // stage.setAlwaysOnTop(true); --- 窗口是否置顶
    private void InitStage(Stage stage) {
        //用来确定stage的长宽
        Scene scene = new Scene(pane, 400, 400);
        scene.setFill(null);
        //添加样式
//        scene.getStylesheets().add(Objects.requireNonNull(
//                getClass().getResource("application.css")
//        ).toExternalForm());
        stage.setScene(scene);
        stage.setX(850);
        stage.setY(400);
        // 这里可以设置手动调整
        stage.setAlwaysOnTop(true);
        //修改任务栏图标
//        stage.getIcons().add(resourceGetter.get(Constant.ImageShow.iconImage));
        // 背景透明
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setOnCloseRequest(event -> {
            event.consume();
            //展示告别动画
            interfaceFunction.exit();
            //写入用户设置  FigureManager
            FigureConfig.storeUserSetting();
        });
    }

//    初始化
    private void InitImageView() {
        LOG.info("开始初始化ImageView");
        Image image = resourceGetter.get(Constant.ImageShow.STANDBY);
        //设置需要显示的图片
        imageView.setImage(image);

        imageView.setX(0);
        imageView.setY(0);
        //Layout是相对Pane的偏移坐标 取消Stage的背景透明就能看得更清楚
        imageView.setLayoutX(0);
        imageView.setLayoutY(50);
        // 设置图片显示大小
        imageView.setFitHeight(Constant.ImageShow.ImageHeight);
        imageView.setFitWidth(Constant.ImageShow.ImageWidth);
        // 保留width:height比例
        imageView.setPreserveRatio(true);
        // 透明背景
        imageView.setStyle("-fx-background:transparent;");

        LOG.info("初始化ImageView成功");
    }

}
