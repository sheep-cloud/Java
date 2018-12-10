package cn.colg;

import org.junit.After;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试基础类
 *
 * @author colg
 */
@Slf4j
public abstract class BaseTest {
    
    @After
    public void tearDown() throws Exception {
        log.info("------------------------------------------------------------------------------------------");
    }
}
