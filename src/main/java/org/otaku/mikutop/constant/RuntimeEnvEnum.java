package org.otaku.mikutop.constant;

/**
 * @author Tieria
 * @date 2022/2/10 14:59
 * @description: 运行环境枚举类
 */

public enum RuntimeEnvEnum{
    DEVELOPMENT("开发环境","1"),
    RELEASE("发布环境", "2");

    private final String seasonName;
    private final String seasonDesc;

     RuntimeEnvEnum(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
}
