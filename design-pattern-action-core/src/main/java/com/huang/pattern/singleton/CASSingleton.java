package com.huang.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 助AtomicReference的CAS来实现单例，同样可以保证对象延迟初始化和线程安全。
 * <p>
 * 优点：1 延迟初始化 2 CAS保证线程安全
 * 缺点：1 可以通过反射、序列化破坏单例 2 可能出现对象被多次初始化，但只有一个线程能够更新成功,请看优化版本{@link CASOptimizationSingleton}
 * Created by JeffreyHy on 2018/2/9.
 */
public class CASSingleton {
    private final Logger logger = LoggerFactory.getLogger(CASSingleton.class);
    private static AtomicReference<CASSingleton> INSTANCE = new AtomicReference();

    private CASSingleton() {
        logger.info("init");
    }

    public static CASSingleton getInstance() {
        while (true) {
            CASSingleton instance = INSTANCE.get();
            if (instance != null) {
                return instance;
            }
            instance = new CASSingleton();
            if (INSTANCE.compareAndSet(null, instance)) {
                return instance;
            }
        }
    }

    public void sayHello() {
        logger.info("hello world !");
    }
}
