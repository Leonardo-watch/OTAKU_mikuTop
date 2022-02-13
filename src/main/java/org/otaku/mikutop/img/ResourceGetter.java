package org.otaku.mikutop.img;

import javafx.scene.image.Image;
import org.otaku.mikutop.util.PathFileUtil;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * @author Tieria
 * @date 2022/2/7
 * @description: 项目资源加载器
 */
public class ResourceGetter {

    //维护的是路径与对应的动作image
    private static final Map<String, Image> IMAGES = new WeakHashMap<>();
    //单例模式
    private static ResourceGetter singleton;

    public ResourceGetter() {
    }

    public static ResourceGetter getInstance() {
        if(singleton == null) {singleton = new ResourceGetter();}
        return singleton;
    }

    //path 和 spec 是需要分开
    public Image get(String path) {
        if(!IMAGES.containsKey(path)){
            IMAGES.put(path, new Image(Objects.requireNonNull(PathFileUtil.gainProgramPath(path))));
        }
        return IMAGES.get(path);
    }

}
