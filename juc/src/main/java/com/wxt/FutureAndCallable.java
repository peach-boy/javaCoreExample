package com.wxt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future+Callable多线程执行任务，并获取返回值
 */
public class FutureAndCallable {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(() -> "task result A");
        callableList.add(() -> "task result B");
        List<Future<String>> futureList = new ArrayList<>();
        try {
            futureList = executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<String> future : futureList) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }
}
