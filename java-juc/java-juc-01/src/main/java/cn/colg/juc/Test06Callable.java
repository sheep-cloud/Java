package cn.colg.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

/**
 * 1. 创建执行线程的方式三: 实现 Callable 接口. 相较于实现 Runnable 接口的方式, 方法可以有返回值, 并且可以抛出异常.
 * 2. 执行 Callable 方式, 需要 FutureTask 实现类的支持, 用于接收运算结果. FutureTask 是 Future 接口的实现类.
 *
 * @author colg
 */
@Slf4j
public class Test06Callable {

    public static void main(String[] args) {
        ThreadDemo1 td = new ThreadDemo1();
        
        // 执行 Callable 方式, 需要 FutureTask 实现类的支持, 用于接收运算结果. FutureTask 是 Future 接口的实现类.
        FutureTask<Integer> futureTask = new FutureTask<>(td);
        
        new Thread(futureTask).start();
        
        // 接收线程运算后的结果
        try {
            Integer sum = futureTask.get();
            log.info("sum: {}", sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class ThreadDemo1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100; i++) {
            log.info("当前值: {}", i);
            sum += i;
        }

        return sum;
    }

}

/*
class ThreadDemo1 implements Runnable {

    @Override
    public void run() {}

}
*/