package com.wxt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReenTrantLock和Condition配合使用
 * 给定一个字符串，比如“12A34B56C78D910E1112F1314G1516H1718I1920J2122K”
 * 要求：两个线程交替打印， 一个打印数字，一个打印字符。
 */
public class ReenTrantLockAndConditionExample {
    private volatile int i = 0;

    public static void main(String[] args) {
        String str = "12A34B56C78D910E1112F1314G1516H1718I1920J2122K";
        new ReenTrantLockAndConditionExample().crossPrint(str);
    }

    public void crossPrint(String str) {
        char[] charArray = str.toCharArray();
        ReentrantLock lock = new ReentrantLock();
        Condition isNumber = lock.newCondition();
        Condition isLetter = lock.newCondition();

        //print letter
        new Thread(() -> {
            for (; i < charArray.length; ) {
                lock.lock();
                try {
                    if (Character.isLetter(charArray[i])) {
                        System.out.println("letter--:" + charArray[i] + ":" + i);
                        isLetter.signal();
                        i++;
                    } else {
                        isNumber.signal();
                        isLetter.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        //print number
        new Thread(() -> {
            for (; i < charArray.length; ) {
                lock.lock();
                try {
                    if (Character.isDigit(charArray[i])) {
                        System.out.println("number--:" + charArray[i] + ":" + i);
                        isNumber.signal();
                        i++;
                    } else {
                        isLetter.signal();
                        isNumber.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
