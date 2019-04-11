package cn.colg.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 单线程: 主线程
 *
 * @author colg
 */
@Slf4j
public class Test01Main {

    public static void main(String[] args) {
        method2("colg");
        log.info("线程名: {}", Thread.currentThread().getName());
    }

    public static void method1(String str) {
        log.info("线程名: {}", Thread.currentThread().getName());
        log.info("method1...");
        log.info(str);
    }

    public static void method2(String str) {
        log.info("线程名: {}", Thread.currentThread().getName());
        log.info("method2...");
        method1(str);
    }
}
