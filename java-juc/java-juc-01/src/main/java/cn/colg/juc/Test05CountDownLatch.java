package cn.colg.juc;

import java.util.concurrent.CountDownLatch;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CountDownLatch: 闭锁, 在完成某些运算时, 只有其它所有线程的运算全部完成, 当前运算才继续执行
 *
 * @author colg
 */
@Slf4j
public class Test05CountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        CountDownLatchDemo cdld = new CountDownLatchDemo(countDownLatch);

        long preTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(cdld).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("耗费时间为: {}ms", DateUtil.spendMs(preTime));

    }
}

@Slf4j
@AllArgsConstructor
class CountDownLatchDemo implements Runnable {

    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                if (i % 2 == 0) {
                    log.info("线程名: {}, 当前偶数: {}", Thread.currentThread().getName(), i);
                }
            }
        } finally {
            // 计数器递减
            countDownLatch.countDown();
        }
    }

}
