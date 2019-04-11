package cn.colg.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建一个子线程, 完成1~100之间自然数的输出. 同样的, 主线程执行同样的操作
 * 
 * <pre>
 *  2. 创建线程的第二种方式: 实现 java.lang.Runnable 接口
 * </pre>
 *
 * @author colg
 */
@Slf4j
public class Test03Runnable {

    public static void main(String[] args) {
        SubRunnable sr = new SubRunnable();
        // 3. 通过Thread类有参构造器创建线程对象; 将Runnable接口的子类对象作为实际参数传递给Thread类的构造方法中
        Thread thread = new Thread(sr);
        // 4. 调用Thread类的start方法: 开启线程, 调用Runnable子类接口的run方法.
        thread.start();

        for (int i = 1; i <= 100; i++) {
            log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
        }
    }
    
}

// 1. 定义子类, 实现Runnable接口
@Slf4j
class SubRunnable implements Runnable {

    // 2. 子类中重写Runnable接口中的run方法
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
        }
    }
    
}