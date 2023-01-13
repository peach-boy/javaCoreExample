package com.wxt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorCompletionService示例
 */
public class ExecutorCompletionServiceExample {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        //开启10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //结果集
        List<Integer> list = new ArrayList<>();
        List<Future<Integer>> futureList = new ArrayList<>();
        try {
            int taskCount = 10;
            //1.定义CompletionService ExecutorCompletionService是此接口的唯一实现类 需要把线程池传进去
            CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

            //2.添加任务（向CompletionService添加任务 然后把返回的futrue添加到futureList即可）
            for (int i = 0; i < taskCount; i++) {
                futureList.add(completionService.submit(new Task(i + 1)));
            }

            //==================结果归集===================
            //方法1：future是提交时返回的，遍历queue则按照任务提交顺序，获取结果  (若是按照提交顺序，那和Futrue的Demo结果将一样，没啥优势可言)
//            for (Future<Integer> future : futureList) {
//                System.out.println("====================");
//                //线程在这里阻塞等待该任务执行完毕,按照
//                Integer result = future.get();
//                System.out.println("任务result=" + result + "获取到结果!" + new Date().getTime());
//                list.add(result);
//            }

            //方法2.使用内部阻塞队列的take()：内部维护阻塞队列，任务先完成的先获取到
            for (int i = 0; i < taskCount; i++) {
                System.out.println("====================");
                //采用completionService.take()，
                Integer result = completionService.take().get();
                System.out.println("任务i==" + result + "完成!" + new Date().getTime());
                list.add(result);
            }

            System.out.println("list=" + list);
            System.out.println("总耗时=" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            //关闭线程池
        }
    }

    static class Task implements Callable<Integer> {
        Integer i;

        public Task(Integer i) {
            super();
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
            if (i == 5) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "任务i=" + i + ",执行完成！");
            return i;
        }

    }

}
