package cn.colg.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 分支/合并框架
 *
 * @author colg
 */
@Slf4j
public class Test15ForkJoinPool {

    public static void main(String[] args) {
        Instant startInclusive = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0, 100000000L);

        Long sum = pool.invoke(task);

        log.info("sum: {}", sum);
        log.info("耗费时间: {}ms", Duration.between(startInclusive, Instant.now()).toMillis());
    }

    @Test
    public void test1() throws Exception {
        Instant startInclusive = Instant.now();

        long sum = 0L;
        for (long i = 0; i <= 100000000L; i++) {
            sum += i;
        }
        log.info("sum: {}", sum);
        log.info("耗费时间: {}ms", Duration.between(startInclusive, Instant.now()).toMillis());
    }

    @Test
    public void test2() throws Exception {
        Instant startInclusive = Instant.now();

        Long sum = LongStream.rangeClosed(0L, 100000000L)
                             .parallel()
                             .reduce(0L, (left, right) -> Long.sum(left, right));

        log.info("sum: {}", sum);
        log.info("耗费时间: {}ms", Duration.between(startInclusive, Instant.now()).toMillis());
    }

}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;

    /** 临界值 */
    public static final long THURSHOLD = 1000000L;

    private long start;
    private long end;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long lentgh = end - start;
        if (lentgh <= THURSHOLD) {
            long sum = 0L;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {
            // 大于临界值, 进行拆分, 压入线程队列
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }

}