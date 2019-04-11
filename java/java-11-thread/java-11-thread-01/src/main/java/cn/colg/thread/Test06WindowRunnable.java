package cn.colg.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现方式: 模拟火车站售票窗口, 开启三个窗口售票, 总漂数为100张
 * 
 * <pre>
 *  此程序存在线程的安全问题, 打印车票时, 会出现重票, 错票
 * </pre>
 *
 * @author colg
 */
public class Test06WindowRunnable {

    public static void main(String[] args) {
        WindowRunnable w1 = new WindowRunnable();

        new Thread(w1, "窗口1").start();
        new Thread(w1, "窗口2").start();
        new Thread(w1, "窗口3").start();
    }

}

@Slf4j
class WindowRunnable implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                ThreadUtil.sleep(20);
                log.info("{} 售票; 票号为: {}", Thread.currentThread().getName(), ticket--);
            } else {
                break;
            }
        }
    }

}