package cn.colg.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 此程序存在线程的安全问题, 打印车票时, 会出现重票, 错票
 * 
 * <pre>
 * 1. 线程安全问题存在的原因?
 *  由于一个线程在操作共享数据的过程中, 未执行完毕的情况下, 另外的线程参与进来, 导致共享数据存在安全问题.
 *  
 * 2. 如何来解决线程的安全问题?
 *  必须让一个线程操作共享数据完毕以后, 其它线程才有机会参与共享数据的操作.
 *  
 * 3. Java如何实现线程的安全: 线程的同步机制
 *  方式一: 同步代码块
 *      synchronized (同步监视器) {
 *          // 需要被同步的代码块(即为操作共享数据的方法)
 *      }
 *      1. 共享数据: 多个线程共同操作的同一个数据(变量)
 *      2. 同步监视器: 由一个类的对象来充当. 哪个线程获取此监视器, 谁就执行大括号里被同步的代码. 俗称: 锁.
 *          注意:
 *              1. 要求所有的线程必须共用同一把锁
 *              2. 在实现的方式中, 考虑同步的话, 可以使用this来充当锁; 但是在继承的方式中, 慎用this
 *  方式二: 同步方法
 *      将操作共享数据的方法声明为 synchronized. 即此方法为同步方法, 能够保证当其中一个线程执行此方法时, 其它线程在外等待直至此线程执行完此方法.
 *      同步方法的锁: this
 * </pre>
 *
 * @author colg
 */
public class Test07ThreadSynchronized {

    public static void main(String[] args) {
        WindowSynchronized w1 = new WindowSynchronized();

        new Thread(w1, "窗口1").start();
        new Thread(w1, "窗口2").start();
        new Thread(w1, "窗口3").start();

    }

}

/*
// 同步代码块
@Slf4j
class WindowSynchronized implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            // 同步代码块: this 表示当前对象, (new WindowSynchronized)
            synchronized (this) {
                if (ticket > 0) {
                    ThreadUtil.sleep(20);
                    log.info("{} 售票; 票号为: {}", Thread.currentThread().getName(), ticket--);
                } else {
                    break;
                }
            }
        }
    }

}
*/

// 同步方法
@Slf4j
class WindowSynchronized implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    // 同步方法
    private synchronized void show() {
        if (ticket > 0) {
            ThreadUtil.sleep(20);
            log.info("{} 售票; 票号为: {}", Thread.currentThread().getName(), ticket--);
        }
    }

}