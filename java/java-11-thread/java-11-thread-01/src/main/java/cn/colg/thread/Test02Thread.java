package cn.colg.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现方式: 创建一个子线程, 完成1~100之间自然数的输出. 同样的, 主线程执行同样的操作
 * 
 * <pre>
 *  1. 创建线程的第一种方式: 继承 java.lang.Thread 类
 * </pre>
 *
 * @author colg
 */
@Slf4j
public class Test02Thread {

    public static void main(String[] args) {
        // 3. 创建Thread子类对象, 即创建了线程对象
        SubThread st = new SubThread();
        // 4. 调用线程对象start方法: 启动线程, 调用run方法
        // 一个线程只能够执行一次start()
        st.start();

        for (int i = 1; i <= 100; i++) {
            log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
        }

    }

}

// 1. 定义子类, 继承Thread类
@Slf4j
class SubThread extends Thread {

    // 2. 子类中重写Thread类中的run方法
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i);
        }
    }

}
