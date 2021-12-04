package com.wxt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore示例
 * 10个线程共享5个资源
 */
public class SemaphoreExample {
    private static final int THREAD_COUNT = 10;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获得了信号量" + "---当前信号量:" + semaphore.availablePermits() + "---等待信号量的线程数:" + semaphore.getQueueLength() + "---" + System.currentTimeMillis());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("-------"+Thread.currentThread().getName() + "释放了信号量------" + "---当前信号量:" + semaphore.availablePermits() + "---等待信号量的线程数:" + semaphore.getQueueLength() + System.currentTimeMillis());
                    semaphore.release();
                }
            });
        }
        threadPool.shutdown();
    }

}
