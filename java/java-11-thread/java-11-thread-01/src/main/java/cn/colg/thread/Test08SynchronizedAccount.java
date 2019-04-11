package cn.colg.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 多线程案例
 * 
 * <pre>
 * 银行有一个账户.
 * 有两个储户分别向同一个账户存3000元, 每次存1000, 存3次. 每次存完打印账户余额.
 * 
 * 1. 是否涉及到多线程?
 *  是, 有两个储户(两种方式创建多线程)
 * 2. 是否有共享数据?
 *  有, 是同一个账户
 * 3. 得考虑线程同步(两种方式处理线程安全)
 * 
 * 拓展问题: 可否实现两个储户交替存钱的操作, 需要使用线程通信!
 * </pre>
 *
 * @author colg
 */
public class Test08SynchronizedAccount {

    public static void main(String[] args) {
        Account account = new Account();

        new Thread(account, "Jack").start();
        new Thread(account, "Rose").start();
    }

}

@Slf4j
class Account implements Runnable {

    private double balance;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            setMoney(1000);
        }
    }

    /**
     * 存钱
     *
     * @author colg
     */
    public synchronized void setMoney(double money) {
        notify();
        
        ThreadUtil.sleep(20);
        balance += 1000;
        log.info("储户: {}; 当前账户余额: {}", Thread.currentThread().getName(), balance);
        
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}