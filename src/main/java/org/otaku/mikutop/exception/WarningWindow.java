package org.otaku.mikutop.exception;

import org.otaku.mikutop.constant.Constant;

import java.awt.*;

/**
 * @author Tieria
 * @date 2022/2/9 9:43
 * @description: 警告窗口定义
 *
 * 这个类因为自己拿了一个SystemTray 生成弹窗的同时会在系统托盘中生成图标...
 * 需要修改
 */
public class WarningWindow {

    public static void display(String message, TrayIcon.MessageType type) {
        if(!SystemTray.isSupported()) {
            System.err.println("System tray not supported!");
        }
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage(Constant.ImageShow.RUNTIME_PATH + "resource/xiaohei/img/icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon");
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        trayIcon.displayMessage(type.name(), message, type);
    }

}
