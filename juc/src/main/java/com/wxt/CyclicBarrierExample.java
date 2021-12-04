package com.wxt;


import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier示例
 * 四个线程并发统计四家银行的流水，主线程输出结果
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("----统计开始" + System.currentTimeMillis());
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                //开始统计银行的流水
                sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("----统计结束" + System.currentTimeMillis());


        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        sheetBankWaterCount.put("result", result);
        System.out.println("----统计结果" + result);
        executor.shutdown();
    }
}
