package com.wxt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch示例
 * 两个DoneTask在三个workTask执行完之后再执行，三个workTask使用countDownLatch同步
 */
public class CountDownLatchExample {

    private static class WorkTask extends Thread {
        private CountDownLatch countDownLatch;

        public WorkTask(String name, CountDownLatch countDownLatch) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.getName() + "启动了" + System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(this.getName() + "执行完了" + System.currentTimeMillis());
                countDownLatch.countDown();
            }
        }
    }

    private static class DoneTask extends Thread {
        private CountDownLatch countDownLatch;

        public DoneTask(String name, CountDownLatch countDownLatch) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.getName() + "-------开始等待" + System.currentTimeMillis());
                countDownLatch.await();
                System.out.println(this.getName() + "执行完了" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new DoneTask("DonwTask1", countDownLatch));
        service.submit(new DoneTask("DoneTask2", countDownLatch));
        service.submit(new WorkTask("workTask1", countDownLatch));
        service.submit(new WorkTask("workTask2", countDownLatch));
        service.submit(new WorkTask("workTask3", countDownLatch));
    }
}
