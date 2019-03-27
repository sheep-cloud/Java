package cn.colg.juc;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程8锁
 * 
 * <pre>
 * 题目: 判断打印的"one" or "two"? 
 *  1. 两个普通同步方法, 两个线程, 标准打印?                                        // one two
 *  2. 新增 ThreadUtil.sleep(2000); 给getOne(), 打印?                        // one two 
 *  3. 新增 普通方法 getThree(), 打印?                                         // three one two
 *  4. 两个普通同步方法, 两个Number对象, 打印?                                    // two one
 *  5. 修改 getOne()为静态同步方法, 打印?                                        // two one
 *  6. 修改两个方法均为静态同步方法, 一个Number对象, 打印?                            // one two
 *  7. 一个静态同步方法, 一个非静态同步方法, 两个Number对象, 打印?                      // two one
 *  8. 两个静态同步方法, 两个Number对象, 打印?                                    // one two
 *  
 * 线程八锁的关键:
 *  1. 非静态方法的锁默认为 this, 静态方法的锁为对应的 Class 实例
 *  2. 在某一时刻内, 只能有一个线程持有锁, 无论几个方法.
 * </pre>
 *
 * @author colg
 */
public class Test10Thread8Monitor {

    public static void main(String[] args) {
        // Number number = new Number();
        // Number number2 = new Number();

        new Thread(new Runnable() {

            @Override
            public void run() {
                // number.getOne();
                Number.getOne();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                Number.getTwo();
            }
        }).start();

        /*
        new Thread(new Runnable() {
        
            @Override
            public void run() {
                number.getThree();
            }
        }).start();
        */
    }

}

@Slf4j
class Number {

    public static synchronized void getOne() {
        ThreadUtil.sleep(2000);
        log.info("one");
    }

    public static synchronized void getTwo() {
        log.info("two");
    }

    public void getThree() {
        log.info("three");
    }

}