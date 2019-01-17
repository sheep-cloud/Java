package cn.colg;

import org.junit.After;
import org.junit.Before;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试基础类
 *
 * @author colg
 */
@Slf4j
public abstract class BaseTest {
    
    private long time;

    @Before
    public void before() throws Exception {
        time = System.currentTimeMillis();
    }

    @After
    public void after() throws Exception {
        log.info("Junit: [{}ms]", DateUtil.spendMs(time));
        log.info("----------------------------------------------------------------------------------------------------");
    }
    
}
