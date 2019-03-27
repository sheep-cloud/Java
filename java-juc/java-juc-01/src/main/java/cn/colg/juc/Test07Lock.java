package cn.colg.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于解决多线程的安全问题
 * 
 * <pre>
 *  synchronized: 隐式锁
 *      1. 同步代码块
 *      2. 同步方法
 *      
 *  jdk1.5 后
 *      3. 同步锁 Lock
 *      注意: 是一个显式锁, 需要通过 lock() 方法上锁, 必须通过 unlock() 方法进行释放锁
 * </pre>
 *
 * @author colg
 */
public class Test07Lock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }

}

@Slf4j
class Ticket implements Runnable {

    private int tick = 10;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true) {
            // 同步代码块
            /*
            synchronized (this) {
                if (tick > 0) {
                    ThreadUtil.sleep(20);
                    log.info("{} 完成售票, 余票为: {}", Thread.currentThread().getName(), --tick);
                } else {
                    break;
                }
            }
            */

            // 同步锁
            // 上锁
            lock.lock();

            try {
                if (tick > 0) {
                    ThreadUtil.sleep(20);
                    log.info("{} 完成售票, 余票为: {}", Thread.currentThread().getName(), --tick);
                } else {
                    break;
                }
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

}