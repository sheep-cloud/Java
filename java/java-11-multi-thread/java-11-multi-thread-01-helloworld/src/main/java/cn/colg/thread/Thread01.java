package cn.colg.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 需求： 创建一个子线程，完成1-100之间自然数的输出。同样的，主线程执行同样的操作
 *
 * @author colg
 */
@Slf4j
public class Thread01 {

    public static void main(String[] args) {
        Thread01SubThread subThread = new Thread01SubThread();
        subThread.setName("子线程");
        subThread.start();

        Thread thread = Thread.currentThread();
        thread.setName("主线程");
        for (int i = 1, length = 100; i <= length; i++) {
            log.info("Thread01.main() >> 线程名 : {}, 自然数 : {}", Thread.currentThread().getName(), i);
        }
    }
}

@Slf4j
class Thread01SubThread extends Thread {

    /*
     * colg [创建多线程的第一种方式：继承Thread类]
     *  1. 定义子类继承Thread类
     *  2. 子类中重写Thread类中的run()方法
     *  3. 创建Thread子类对象，即创建了线程对象
     *  4. 调用线程对象的start()方法：启动线程，调用run()方法。
     *  注意：
     *      1. 一个线程只能执行一次start()
     *      2. 不能通过Thread实现类对象的run()方法去启动一个线程
     */

    @Override
    public void run() {
        int length = 100;
        for (int i = 1; i <= length; i++) {
            log.info("Thread01.run() >> 线程名 : {}, 自然数 : {}", Thread.currentThread().getName(), i);
        }
    }
}
