package com.huang.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CASSingleton升级版，避免对象被多次初始化 {@link CASSingleton}
 * Created by JeffreyHy on 2018/2/9.
 */
public class CASOptimizationSingleton {
    private final Logger logger = LoggerFactory.getLogger(CASSingleton.class);
    private static AtomicBoolean isInit = new AtomicBoolean(false);
    private static volatile CASOptimizationSingleton instance = null;

    private CASOptimizationSingleton() {
        logger.info("init");
    }

    public static CASOptimizationSingleton getInstance() {
        while (instance == null) {
            if (isInit.compareAndSet(false, true)) {
                instance = new CASOptimizationSingleton();
                break;
            }
        }
        return instance;
    }

    public void sayHello() {
        logger.info("hello world !");
    }
}
