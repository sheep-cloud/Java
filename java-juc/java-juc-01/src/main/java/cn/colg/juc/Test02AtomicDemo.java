package cn.colg.juc;

import java.util.concurrent.atomic.AtomicInteger;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * i++ 的原子性问题
 * 
 * <pre>
 *  // i++ 的操作实际上分为三个步骤: "读 -> 改 -> 写"
 *  int i = 10;
 *  i = i++; // 10
 *  
 *  底层操作:
 *  int temp = i;
 *  i = i + 1;
 *  i = temp;
 * </pre>
 *
 * @author colg
 */
/**
 * 原子变量
 * 
 * <pre>
 *  1. i++ 的原子性问题
 *      // i++ 的操作实际上分为三个步骤: "读 -> 改 -> 写"
 *      int i = 10;
 *      i = i++; // 10
 *      
 *      底层操作:
 *      int temp = i;
 *      i = i + 1;
 *      i = temp;
 *      
 *  2. 原子变量: 在jdk1.5后, java.util.concurrent.atomic 包下提供了常用的原子变量.
 *      1. volatile 保证内存可见性
 *      2. CAS(Compare-And-Swap) 算法保证数据的原子性
 *          CAS 算法是硬件对于并发操作共享数据的支持
 *          CAS 包含了三个操作数:
 *              内存值     V
 *              预估值     A
 *              更新值     B
 *              当前仅当 V == A 时, V = B, 否则不做任何操作
 * </pre>
 *
 * @author colg
 */
public class Test02AtomicDemo {
    
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
    
}

@Slf4j
class AtomicDemo implements Runnable {

    // private Integer serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger(0);

    public Integer getSerialNumber() {
        // return serialNumber++;
        return serialNumber.getAndIncrement();
    }

    @Override
    public void run() {
        ThreadUtil.sleep(200);

        log.info("线程名: {}; 当前值: {}", Thread.currentThread().getName(), this.getSerialNumber());
    }

}
