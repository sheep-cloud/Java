package cn.colg.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程池
 * 
 * <pre>
 * 1. 线程池: 提供了一个线程队列, 队列中保存着所有等待状态的线程. 避免了创建于销毁线程的额外开销, 提高了响应的速度.
 * 
 * 2. 线程池的体系结构:
 *  java.util.concurrent.Executor: 负责线程的使用和调度的根接口
 *      |-- java.util.concurrent.ExecutorService: 线程池的主要子接口
 *          |-- java.util.concurrent.ThreadPoolExecutor: 线程池的实现类
 *          |-- java.util.concurrent.ScheduledExecutorService: 负责线程调度的子接口
 *              |-- java.util.concurrent.ScheduledThreadPoolExecutor: 继承ThreadPoolExecutor, 实现ScheduledExecutorService
 *              
 * 3, 工具类: Executors
 *  java.util.concurrent.Executors.newFixedThreadPool(int): 创建固定大小的线程池
 *  java.util.concurrent.Executors.newCachedThreadPool(): 缓存线程池, 线程池的数量不固定, 可以根据需求自动的更改数量
 *  java.util.concurrent.Executors.newSingleThreadScheduledExecutor(): 创建单个线程池, 线程池中只有一个线程
 * 
 *  java.util.concurrent.Executors.newScheduledThreadPool(int): 创建固定大小的线程池, 可以延迟或定时的执行任务
 * </pre>
 *
 * @author colg
 */
public class Test11ThreadPool {

    public static void main(String[] args) {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();

        /*
        new Thread(threadPoolDemo).start();
        new Thread(threadPoolDemo).start();
        */

        // 1. 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 2. 为线程池中的线程分配任务
        pool.submit(threadPoolDemo);

        // 3. 关闭线程池
        pool.shutdown();
    }

}

@Slf4j
class ThreadPoolDemo implements Runnable {

    private int i = 0;

    @Override
    public void run() {
        while (i <= 100) {
            log.info("线程名: {}; 值: {}", Thread.currentThread().getName(), i++);
        }
    }

}