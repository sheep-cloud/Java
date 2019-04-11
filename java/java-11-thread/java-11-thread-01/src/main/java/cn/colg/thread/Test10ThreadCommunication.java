package cn.colg.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程通信
 * 
 * <pre>
 * 使用两个线程打印1~100. 线程1, 线程2 交替打印
 * 
 * 线程通信:
 * java.lang.Object.wait():             一旦一个线程执行到wait(), 就释放当前的锁.
 * java.lang.Object.notify():           唤醒wait的一个线程
 * java.lang.Object.notifyAll():        唤醒wait的所有线程
 * 
 * </pre>
 *
 * @author colg
 */
public class Test10ThreadCommunication {

    public static void main(String[] args) {
        PrintNum pn = new PrintNum();

        new Thread(pn, "A").start();;
        new Thread(pn, "B").start();
    }

}

@Slf4j
class PrintNum implements Runnable {

    private int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                
                if (num <= 100) {
                    ThreadUtil.sleep(20);
                    log.info("线程名: {}; 当前值: {}", Thread.currentThread().getName(), num++);
                } else {
                    break;
                }
                
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}