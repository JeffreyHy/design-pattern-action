package com.huang.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 饿汉单例
 * 优点：支持延迟加载
 * 缺点：1 效率低下，实际仅第一次实例化需要保证线程安全，99%情况下不需要同步
 *       2 可以通过反射、序列化破坏单例
 * Created by JeffreyHy on 2018/2/9.
 */
public class FullManSingleton {
    private static final Logger logger = LoggerFactory.getLogger(FullManSingleton.class);
    private static FullManSingleton instance = null;

    private FullManSingleton() {
        logger.info("init ");
    }

    public static synchronized FullManSingleton getInstance() {
        if (instance == null) {
            instance = new FullManSingleton();
        }
        return instance;
    }

    public void sayHello() {
        logger.info("hello world !");
    }
}
