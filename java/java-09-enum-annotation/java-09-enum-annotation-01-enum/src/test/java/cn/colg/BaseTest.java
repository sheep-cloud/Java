package cn.colg;

import org.junit.After;

import cn.hutool.core.lang.Console;

/**
 * 测试基础类
 *
 * @author colg
 */
public abstract class BaseTest {

    @After
    public void tearDown() throws Exception {
        Console.log("----------------------------------------------------------------------------------------------------\n");
    }
}
