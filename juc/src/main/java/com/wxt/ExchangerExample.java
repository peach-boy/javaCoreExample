package com.wxt;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger示例
 * 两个线程交换数据
 */
public class ExchangerExample {
    private static final Exchanger<String> exchanger = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                // A录入银行流水数据
                String str = "银行流水A";
                System.out.println(Thread.currentThread().getName() + str);
                str = exchanger.exchange(str);
                System.out.println(Thread.currentThread().getName() + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(() -> {
            try {
                String str = "银行流水B";
                System.out.println(Thread.currentThread().getName() + str);
                str = exchanger.exchange(str);
                System.out.println(Thread.currentThread().getName() + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
    }
}
