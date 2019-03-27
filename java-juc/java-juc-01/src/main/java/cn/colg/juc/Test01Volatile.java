package cn.colg.juc;

import cn.hutool.core.thread.ThreadUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * volatile 关键字
 * 
 * <pre>
 *  当多个线程进行操作共享数据时, 可以保证内存中的数据可见. 相较于 synchronized 是一种较为轻量级的同步策略.
 *  
 *  注意:
 *      1. volatile 不具备"互斥性"
 *      2. volatile 不能保证变量的"原子性"
 * </pre>
 *
 * @author colg
 */
@Slf4j
public class Test01Volatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        /*
         * colg  [内存可见性问题]
         *  内存可见性问题是, 当多个线程操作共享数据时, 彼此不可见
         */
        
        // 使用 synchronized 加锁, 刷新主存数据
        /*
        while (true) {
            synchronized (td) {
                if (td.getFlag()) {
                    log.info("------------------------------------------------------------------------------------------");
                    break;
                }
            }
        }
        */

        // 使用 volatile 关键字, 实时刷新主存
        while (true) {
            if (td.getFlag()) {
                log.info("td.getFlag(): {}", td.getFlag());
                break;
            }
        }
    }
    
}

@Slf4j
class ThreadDemo implements Runnable {

    @Getter
    @Setter
    // private Boolean flag = false;
    private volatile Boolean flag = false;

    @Override
    public void run() {
        ThreadUtil.sleep(200);

        flag = true;
        log.info("flag: {}", flag);
    }

}