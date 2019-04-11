package cn.colg.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 继承方式: 模拟火车站售票窗口, 开启三个窗口售票, 总漂数为100张
 * 
 * <pre>
 *  此程序存在线程的安全问题, 打印车票时, 会出现重票, 错票
 * </pre>
 *
 * @author colg
 */
public class Test05WindowThread {

    public static void main(String[] args) {
        WindowThread w1, w2, w3;
        w1 = new WindowThread();
        w2 = new WindowThread();
        w3 = new WindowThread();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }

}

@Slf4j
class WindowThread extends Thread {

    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                log.info("{} 售票; 票号为: {}", Thread.currentThread().getName(), ticket--);
            } else {
                break;
            }
        }

    }

}