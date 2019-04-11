package cn.colg.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程常用方法
 * 
 * <pre>
 * 1. java.lang.Thread.start();                     启动线程并执行响应的run()方法
 * 2. java.lang.Thread.run();                       子线程要执行的代码放入run()方法中
 * 3. java.lang.Thread.currentThread();             静态的, 调取当前的线程
 * 4. java.lang.Thread.getName();                   获取此线程的名字
 * 5. java.lang.Thread.setName(String);             设置此线程的名字
 * 6. java.lang.Thread.yield();                     静态的, 调用此方法的线程释放当前CPU的执行权
 * 7. java.lang.Thread.join();                      在A线程中调用B线程的join方法, 表示当执行到此方法, A线程停止执行, 直至B线程执行完毕, A线程再接着join()之后的代码执行
 * 8. java.lang.Thread.isAlive();                   判断线程是否还存货
 * 9. java.lang.Thread.sleep(long);                 显式的让当前线程睡眠
 * 
 * 10. 线程通信
 *  10.1. java.lang.Object.wait();
 *  10.2. java.lang.Object.notify();
 *  10.3. java.lang.Object.notifyAll();
 *  
 * 11. 设置线程的优先级
 * </pre>
 *
 * @author colg
 */
@Slf4j
public class Test04ThreadMethod {

    public static void main(String[] args) throws InterruptedException {
        SubThreadMethod st = new SubThreadMethod();
        st.setName("子线程1");
        st.setPriority(Thread.MAX_PRIORITY);
        st.start();

        Thread currentThread = Thread.currentThread();
        currentThread.setName("main 线程");
        for (int i = 1; i <= 100; i++) {
            /*
            if (i == 20) {
                // Thread.yield();
                st.join();
            }
            */
            
            log.info("线程名: {}; 优先级: {}; 值: {}", currentThread.getName(), currentThread.getPriority(), i);
        }
    }

}

@Slf4j
class SubThreadMethod extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            Thread currentThread = Thread.currentThread();
            log.info("线程名: {}; 优先级: {}; 值: {}", currentThread.getName(), currentThread.getPriority(), i);
        }
    }
    
}