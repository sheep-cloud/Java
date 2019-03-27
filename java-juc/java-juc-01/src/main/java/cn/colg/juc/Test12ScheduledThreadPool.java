package cn.colg.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程调度
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
@Slf4j
public class Test12ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1. 创建线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        
        // 2. 线程调度
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.schedule(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int randomInt = RandomUtil.randomInt(100);
                    log.info("线程名: {}; 随机数: {}", Thread.currentThread().getName(), randomInt);
                    return randomInt;
                }
            }, 1, TimeUnit.SECONDS);
            
            log.info("结果: {}", future.get());
        }
        
        // 3. 关闭线程池
        pool.shutdown();
        
    }

}
