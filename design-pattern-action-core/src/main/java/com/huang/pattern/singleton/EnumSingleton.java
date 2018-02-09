package com.huang.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 枚举单例
 * 优点：保证只会产生一个实例，反射，序列化都无法创建多个实例。
 * 缺点：1 不能实现单例对象的延迟加载
 * Created by JeffreyHy on 2018/2/8.
 */
public enum EnumSingleton {
    INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(EnumSingleton.class);

    private EnumSingleton() {
        logger.info("init----");
    }

    public void sayHello() {
        logger.info("hello world !");
    }
}
