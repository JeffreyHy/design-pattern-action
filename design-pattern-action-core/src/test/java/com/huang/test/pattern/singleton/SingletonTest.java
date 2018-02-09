package com.huang.test.pattern.singleton;

import com.huang.pattern.singleton.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JeffreyHy on 2018/2/8.
 */
public class SingletonTest extends TestCase {
    private static final Logger logger = LoggerFactory.getLogger(SingletonTest.class);
    public static final int THREAD_COUNT = 10;

    @Test
    public void testHungrySingleton() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);//控制10个线程同时获取SingletonA实例
        final CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    HungrySingleton single = HungrySingleton.getInstance();
                    single.sayHello();
                    logger.info(single.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitLatch.countDown();
                }

            });
        }
        latch.countDown();
        executorService.shutdown();
        waitLatch.await();//等待任务完成主线程再退出
    }

    /**
     * 反射破坏单例
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void testHungrySingletonByReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HungrySingleton one = HungrySingleton.getInstance();
        Constructor<HungrySingleton> constructor = HungrySingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        HungrySingleton two = constructor.newInstance();
        assertTrue(one != two);
    }

    /**
     * 序列化破坏单例
     * 解决办法：序列化会通过反射调用无参数的构造方法创建一个新的对象。因此可重写readResolve方法返回当前实例，防止单例破坏
     * <pre>
     *        private Object readResolve() {
     *            return INSTANCE;
     *        }
     * </pre>
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testHungrySingletonBySerialization() throws IOException, ClassNotFoundException {
        HungrySingleton one = HungrySingleton.getInstance();
        //Write Obj to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(one);
        //Read Obj from file
        File file = new File("tempFile");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        HungrySingleton two = (HungrySingleton) ois.readObject();
        assertTrue(one != two);
    }

    @Test
    public void testSingletonEnum() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);//控制10个线程同时获取SingletonA实例
        final CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    EnumSingleton single = EnumSingleton.INSTANCE;
                    single.sayHello();
                    logger.info(single.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitLatch.countDown();
                }

            });
        }
        latch.countDown();
        executorService.shutdown();
        waitLatch.await();//等待任务完成主线程再退出
    }

    @Test
    public void testSingletonEnumByReflection() {
        EnumSingleton one = EnumSingleton.INSTANCE;
        try {
            Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            EnumSingleton two = constructor.newInstance();
            assertTrue(one != two);
        } catch (Exception e) {
            assertTrue(e.getClass() == NoSuchMethodException.class);
        }
    }

    @Test
    public void testFullManSingleton() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);//控制10个线程同时获取SingletonA实例
        final CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    FullManSingleton single = FullManSingleton.getInstance();
                    single.sayHello();
                    logger.info(single.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitLatch.countDown();
                }

            });
        }
        latch.countDown();
        executorService.shutdown();
        waitLatch.await();//等待任务完成主线程再退出
    }

    @Test
    public void testFullManSingletonByReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        FullManSingleton one = FullManSingleton.getInstance();
        Constructor<FullManSingleton> constructor = FullManSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        FullManSingleton two = constructor.newInstance();
        assertTrue(one != two);
    }

    @Test
    public void testDoubleCheckSingleton() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);//控制10个线程同时获取SingletonA实例
        final CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    DoubleCheckSingleton single = DoubleCheckSingleton.getInstance();
                    single.sayHello();
                    logger.info(single.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitLatch.countDown();
                }

            });
        }
        latch.countDown();
        executorService.shutdown();
        waitLatch.await();//等待任务完成主线程再退出
    }

    @Test
    public void testDoubleCheckSingletonByReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DoubleCheckSingleton one = DoubleCheckSingleton.getInstance();
        Constructor<DoubleCheckSingleton> constructor = DoubleCheckSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DoubleCheckSingleton two = constructor.newInstance();
        assertTrue(one != two);
    }


    @Test
    public void testHolderSingleton() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);//控制10个线程同时获取SingletonA实例
        final CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    HolderSingleton single = HolderSingleton.getInstance();
                    single.sayHello();
                    logger.info(single.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitLatch.countDown();
                }

            });
        }
        latch.countDown();
        executorService.shutdown();
        waitLatch.await();//等待任务完成主线程再退出
    }

    @Test
    public void testHolderSingletonByReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HolderSingleton one = HolderSingleton.getInstance();
        Constructor<HolderSingleton> constructor = HolderSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        HolderSingleton two = constructor.newInstance();
        assertTrue(one != two);
    }

    @Test
    public void testCASSingleton() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);//控制10个线程同时获取SingletonA实例
        final CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    CASSingleton single = CASSingleton.getInstance();
                    single.sayHello();
                    logger.info(single.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitLatch.countDown();
                }

            });
        }
        latch.countDown();
        executorService.shutdown();
        waitLatch.await();//等待任务完成主线程再退出
    }

    @Test
    public void testCASSingletonByReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        CASSingleton one = CASSingleton.getInstance();
        Constructor<CASSingleton> constructor = CASSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CASSingleton two = constructor.newInstance();
        assertTrue(one != two);
    }

    @Test
    public void testCASOptimizationSingleton() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);//控制10个线程同时获取SingletonA实例
        final CountDownLatch waitLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    CASOptimizationSingleton single = CASOptimizationSingleton.getInstance();
                    single.sayHello();
                    logger.info(single.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    waitLatch.countDown();
                }

            });
        }
        latch.countDown();
        executorService.shutdown();
        waitLatch.await();//等待任务完成主线程再退出
    }

    @Test
    public void testCASOptimizationSingletonByReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        CASOptimizationSingleton one = CASOptimizationSingleton.getInstance();
        Constructor<CASOptimizationSingleton> constructor = CASOptimizationSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CASOptimizationSingleton two = constructor.newInstance();
        assertTrue(one != two);
    }
}
