package cn.colg.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 生产者和消费者案例
 * 
 * <pre>
 * 生产者(Producer)将产品交给店员(Clerk), 而消费者(Consumer)从店员处取走商品.
 * 店员一次只能持有固定数量的产品(如: 20), 如果生产者试图生产更多的产品, 店员会叫生产者停一下,
 * 如果店中有空位放产品了再通知生产者继续生产, 如果店中没有产品了,店员会告诉消费者等一下, 如果店中有产品了再通知消费者来取走产品.
 * 
 * 1. 是否涉及多线程的问题?
 *  是, 生产者和消费者
 * 2. 是否有共享数据?
 *  有, 产品数量; 考虑线程的安全
 * 3. 是否涉及到线程的通信?
 *  是, 存在着生产者和消费者的通信
 * </pre>
 *
 * @author colg
 */
public class Test11ProductorAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);
        
        new Thread(p1, "生产者A").start();
        new Thread(c1, "消费者A").start();
        
        Producer p2 = new Producer(clerk);
        new Thread(p2, "生产者B").start();
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
     * 生产商品
     *
     * @author colg
     */
    public synchronized void addProduct() {
        if (product >= 20) {
            log.info("已经生产够20个了, 生产者你停一下!");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            product++;
            log.info("线程名: {}; 生产了第 {} 个商品", Thread.currentThread().getName(), product);
            notifyAll();
        }
    }

    /**
     * 消费商品
     *
     * @author colg
     */
    public synchronized void consumeProduct() {
        if (product <= 0) {
            log.info("已经没有商品了, 消费者你等一下!");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("线程名: {}; 消费了第 {} 个商品", Thread.currentThread().getName(), product);
            product--;
            notifyAll();
        }
    }

}

/**
 * 生产者
 *
 * @author colg
 */
@Slf4j
@AllArgsConstructor
class Producer implements Runnable {

    private Clerk clerk;

    @Override
    public void run() {
        log.info("生产者开始生产商品");
        while (true) {
            ThreadUtil.sleep(1000);
            clerk.addProduct();
        }
    }

}

/**
 * 消费者
 *
 * @author colg
 */
@Slf4j
@AllArgsConstructor
class Consumer implements Runnable {

    private Clerk clerk;

    @Override
    public void run() {
        log.info("消费者开始消费产品");
        while (true) {
            ThreadUtil.sleep(1000);
            clerk.consumeProduct();
        }
    }

}