package com.huang.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 饿汉单例
 * 优点：实现简单
 * 缺点：1 不能实现单例对象的延迟加载 2 可以通过反射、序列化破坏单例（见单测）
 * Created by JeffreyHy on 2018/2/8.
 */
public class HungrySingleton implements Serializable {
    private static final long serialVersionUID = -2527616973762474305L;
    private static final Logger logger = LoggerFactory.getLogger(HungrySingleton.class);
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton() {
        logger.info("init----");
    }

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

    public void sayHello() {
        logger.info("hello world !");
    }


}
