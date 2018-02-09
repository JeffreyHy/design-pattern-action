package com.huang.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initialization-on-demand holder idiom
 * <a href="https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom">Initialization-on-demand holder idiom</a>
 * Java语言规范（JLS）规定：JVM在加载class时，类将初始化。由于HolderSingleton没有任何静态变量需要初始化，初始化完成。
 * 静态内部类SingletonHolder只有通过方法或变量使用到时，JVM才会初始化SingletonHolder。当第一次调用getInstance时，
 * JVM将加载并初始化SingletonHolder。此外，类的初始化过程由JVM保证线程安全，因此getInstance方法中不需要进一步的同步。
 * 也就是说，并发调用getInstance，也能保证线程安全，并且不会产生任何额外的同步开销。
 * <p>
 * 优势：1 支持延迟加载 2 避免了同步的开销，性能更好
 * 缺点：可以通过反射、序列化破坏单例
 * <p>
 * Created by JeffreyHy on 2018/2/9.
 */
public class HolderSingleton {
    private final Logger logger = LoggerFactory.getLogger(HolderSingleton.class);

    private HolderSingleton() {
        logger.info("init");
    }

    private static class SingletonHolder {
        static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void sayHello() {
        logger.info("hello world !");
    }
}
