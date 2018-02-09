package com.huang.pattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 双重校验锁单例，饿汉单例的优化版本{@link FullManSingleton}
 * <p>
 * 由于会两次检查 instance == null，第一次不加锁，第二次加锁，因此叫双重校验锁。
 * 但双重校验锁的问题是：并不能保证它会在单处理器或多处理器计算机上顺利运行。这归咎于Java平台内存模型，内存模型允许“无序写入”，
 * 即JIT编译器允许指令重排序。instance = new DoubleCheckSingleton()实际包含了三个过程：
 * 1 分配内存
 * 2 调用构造方法初始化对象
 * 3 instance指向内存地址
 * 当发生指令重排序时，可能按1,3,2的顺序执行，其他线程判断instance == null返回false，而此时对象可能还未初始化，使得其他线程拿到初始化一半的对象。
 * 因此，需要防止指令重排序，可通过volatile关键字来解决。
 * 根据JVM内存模型happens-before规则，对于volatile变量，所有的write都将先行发生于read，从而保证instance的可见性
 * <p>
 * 说明：happens-before监视器锁规则：监视器上的解锁操作happens-before于随后对这个监视器的加锁操作，也就是说synchronized能够保证释放监视器锁之后，
 * 之前所有的操作对后续在同一个监视器上加锁的线程可见。但是第一次判断instance == null与synchronized并不构成happens-before关系，
 * 因此存在可见性问题
 * <p>
 * 优势：实现延迟加载
 * 缺点：1 volatile同样存在开销 2 可以通过反射、序列化破坏单例
 * Created by JeffreyHy on 2018/2/9.
 */
public class DoubleCheckSingleton {
    private final Logger logger = LoggerFactory.getLogger(DoubleCheckSingleton.class);
    private static volatile DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {
        logger.info("init ");
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public void sayHello() {
        logger.info("hello world !");
    }
}
