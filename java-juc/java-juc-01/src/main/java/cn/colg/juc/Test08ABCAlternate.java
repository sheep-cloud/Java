package cn.colg.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程按序交替
 * 
 * <pre>
 * 需求:
 *  编写一个程序, 开启3个线程, 这3个线程的ID分别为A, B, C, 每个线程将自己的ID在屏幕上打印10遍, 要求输出的结果必须按顺序显示.
 *  如: ABCABCABC... 依次递归
 * </pre>
 *
 * @author colg
 */
@Slf4j
public class Test08ABCAlternate {

    public static void main(String[] args) {
        AlternateDemo alternateDemo = new AlternateDemo();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alternateDemo.loopA(i);
                }
            }
        }, "A").start();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alternateDemo.loopB(i);
                }
            }
        }, "B").start();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alternateDemo.loopC(i);
                    
                    log.info("------------------------------------------------------------------------------------------");
                }
            }
        }, "C").start();
    }

}

@Slf4j
class AlternateDemo {

    /** 当前正在执行线程的标记 */
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /**
     * 循环打印A
     *
     * @param totalLoop 循环第几轮
     * @author colg
     */
    public void loopA(int totalLoop) {
        lock.lock();

        try {
            // 1. 判断
            if (number != 1) {
                condition1.await();
            }

            // 2. 打印
            for (int i = 0; i < 5; i++) {
                log.info("线程名: {}; 当前值: {}; 当前第几轮: {}", Thread.currentThread().getName(), i, totalLoop);
            }

            // 3. 唤醒
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 循环打印B
     *
     * @param totalLoop 循环第几轮
     * @author colg
     */
    public void loopB(int totalLoop) {
        lock.lock();

        try {
            // 1. 判断
            if (number != 2) {
                condition2.await();
            }

            // 2. 打印
            for (int i = 0; i < 5; i++) {
                log.info("线程名: {}; 当前值: {}; 当前第几轮: {}", Thread.currentThread().getName(), i, totalLoop);
            }

            // 3. 唤醒
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 循环打印C
     *
     * @param totalLoop 循环第几轮
     * @author colg
     */
    public void loopC(int totalLoop) {
        lock.lock();

        try {
            // 1. 判断
            if (number != 3) {
                condition3.await();
            }

            // 2. 打印
            for (int i = 0; i < 5; i++) {
                log.info("线程名: {}; 当前值: {}; 当前第几轮: {}", Thread.currentThread().getName(), i, totalLoop);
            }

            // 3. 唤醒
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}