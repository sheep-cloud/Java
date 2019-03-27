package cn.colg.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * ReadWriteLock: 读写锁
 * 
 * <pre>
 *  写写/读写   需要"互斥"
 *  读读              不需要"互斥"
 * </pre>
 *
 * @author colg
 */
public class Test09ReadWriteLock {

    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();

        new Thread(new Runnable() {

            @Override
            public void run() {
                rw.write(RandomUtil.randomInt(100));
            }
        }, "Write: 1").start();

        // 10个线程同时去读
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    rw.read();
                }
            }, "Read: " + i).start();
        }
    }

}

@Slf4j
class ReadWriteLockDemo {

    private int number = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 读, 允许多个线程读
     *
     * @author colg
     */
    public void read() {
        // 上读锁
        lock.readLock().lock();

        try {
            log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), number);
        } finally {
            lock.readLock().unlock();
        }

    }

    /**
     * 写, 当前只能有一个线程写
     *
     * @param number
     * @author colg
     */
    public void write(int number) {
        // 上写锁
        lock.writeLock().lock();

        try {
            this.number = number;
            log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), number);
        } finally {
            lock.writeLock().unlock();
        }

    }
}