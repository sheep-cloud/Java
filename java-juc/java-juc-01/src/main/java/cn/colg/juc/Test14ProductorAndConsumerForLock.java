package cn.colg.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 生产者和消费者案例: Lock
 * 
 * <pre>
 * 等待唤醒机制
 * </pre>
 *
 * @author colg
 */
public class Test14ProductorAndConsumerForLock {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Productor1 productor = new Productor1(clerk);
        Consumer1 consumer = new Consumer1(clerk);

        new Thread(productor, "生产者 A").start();
        new Thread(consumer, "消费者 B").start();

        new Thread(productor, "生产者 C").start();
        new Thread(consumer, "消费者 D").start();
    }

}

/**
 * 店员
 *
 * @author colg
 */
@Slf4j
class Clerk1 {

    /** 商品数量 */
    private int product = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 进货
     *
     * @author colg
     */
    public void get() {
        lock.lock();

        try {
            // 为了避免虚假唤醒问题, wait() 应该总是使用在循环中
            while (product >= 1) {
                log.info("产品已满!");

                try {
                    // 等待
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 一次进货一个商品
            log.info("线程名: {}; 商品数量: {}", Thread.currentThread().getName(), ++product);

            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 卖货
     *
     * @author colg
     */
    public void sale() {
        lock.lock();

        try {
            while (product <= 0) {
                log.info("缺货!");

                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 一次卖一个商品
            log.info("线程名: {}; 商品数量: {}", Thread.currentThread().getName(), --product);

            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

/**
 * 生产者
 *
 * @author colg
 */
class Productor1 implements Runnable {

    private Clerk clerk;

    public Productor1(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        // 生产20个商品
        for (int i = 0; i < 20; i++) {
            ThreadUtil.sleep(200);
            clerk.get();
        }
    }
}

/**
 * 消费者
 *
 * @author colg
 */
class Consumer1 implements Runnable {

    private Clerk clerk;

    public Consumer1(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            // 消费20个商品
            clerk.sale();
        }
    }

}