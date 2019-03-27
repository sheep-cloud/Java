package cn.colg.juc;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟 CAS 算法
 *
 * @author colg
 */
@Slf4j
public class Test03CompareAndSwap {

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue, RandomUtil.randomInt(1000));
                    log.info("是否成功: {}", b);
                }
            }).start();
        }
    }

}

class CompareAndSwap {

    private int value;

    /**
     * 获取内存值
     *
     * @return
     * @author colg
     */
    public synchronized int get() {
        return value;
    }

    /**
     * 比较
     *
     * @param expecteValue 预估值
     * @param newValue 更新值
     * @return
     * @author colg
     */
    public synchronized int compareAndSwap(int expecteValue, int newValue) {
        int oldValue = value;

        if (oldValue == expecteValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    /**
     * 设置
     *
     * @param expectedValue 预估值
     * @param newValue 更新值
     * @return
     * @author colg
     */
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
