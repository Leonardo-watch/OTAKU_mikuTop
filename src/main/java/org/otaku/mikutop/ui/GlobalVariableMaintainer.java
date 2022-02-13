package org.otaku.mikutop.ui;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.otaku.mikutop.constant.RuntimeEnvEnum;


/**
 * @author Tieria
 * @date 2022/2/7
 * @description: 全局变量维护者
 * 单例模式
 */
public class GlobalVariableMaintainer {

    //运行环境  这里手动控制
    private static RuntimeEnvEnum RUNTIME_ENV;
    private static GlobalVariableMaintainer singleton;
    private final ImageView imageView;
    private final Stage stage;

    private GlobalVariableMaintainer() {
        this.imageView = new ImageView();
        this.stage = new Stage();
        RUNTIME_ENV = RuntimeEnvEnum.RELEASE;
    }

    public static GlobalVariableMaintainer getInstance() {
        if(singleton != null) {return singleton;}
        return new GlobalVariableMaintainer();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Stage getStage() {
        return stage;
    }

    public RuntimeEnvEnum getRuntimeEnv() {
        return RUNTIME_ENV;
    }

}
