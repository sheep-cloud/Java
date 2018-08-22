package cn.colg;

import lombok.extern.slf4j.Slf4j;

/**
 * 单线程！
 *
 * @author colg
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) {
        method2("colg");
    }

    public static void method1(String str) {
        log.info("MainTest.method1() >> method1");
        log.info("MainTest.method1() >> str : {}", str);
    }

    public static void method2(String str) {
        log.info("MainTest.method2() >> method2");
        method1(str);
    }
}
