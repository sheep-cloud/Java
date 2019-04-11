package cn.colg.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 死锁问题, 处理线程同步时容易出现.
 * 
 * <pre>
 * 不同的线程分别占用对方需要的同步资源不放弃, 都在等待对方放弃自己需要的同步资源, 就形成了线程的死锁
 * </pre>
 *
 * @author colg
 */
@Slf4j
public class Test09DeadLock {

    private static StringBuffer sb1 = new StringBuffer();
    private static StringBuffer sb2 = new StringBuffer();

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (sb1) {
                    ThreadUtil.sleep(20);
                    sb1.append("A");
                    synchronized (sb2) {
                        sb2.append("B");
                        log.info("线程名: {}; sb1: {}; sb2: {}", Thread.currentThread().getName(), sb1, sb2);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sb2) {
                    ThreadUtil.sleep(20);
                    sb1.append("C");
                    synchronized (sb1) {
                        sb2.append("D");
                        log.info("线程名: {}; sb1: {}; sb2: {}", Thread.currentThread().getName(), sb1, sb2);
                    }
                }
            }
        }).start();
    }

}
