package cn.colg.juc;

import cn.hutool.core.thread.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 生产者和消费者案例: synchronized
 * 
 * <pre>
 * 等待唤醒机制
 * </pre>
 *
 * @author colg
 */
public class Test13ProducerAndConsumer {

    public static void main(String[] args) {
        Clerk1 clerk = new Clerk1();

        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

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
class Clerk {

    /** 商品数量 */
    private int product;

    /**
     * 进货
     *
     * @author colg
     */
    public synchronized void get() {
        // 为了避免虚假唤醒问题, wait() 应该总是使用在循环中
        while (product >= 1) {
            log.info("产品已满!");

            try {
                // 等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 一次进货一个商品
        log.info("线程名: {}; 商品数量: {}", Thread.currentThread().getName(), ++product);

        this.notifyAll();
    }

    /**
     * 卖货
     *
     * @author colg
     */
    public synchronized void sale() {
        while (product <= 0) {
            log.info("缺货!");

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 一次卖一个商品
        log.info("线程名: {}; 商品数量: {}", Thread.currentThread().getName(), --product);

        this.notifyAll();
    }
}

/**
 * 生产者
 *
 * @author colg
 */
@AllArgsConstructor
class Productor implements Runnable {

    private Clerk1 clerk;

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
@AllArgsConstructor
class Consumer implements Runnable {

    private Clerk1 clerk;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            // 消费20个商品
            clerk.sale();
        }
    }

}