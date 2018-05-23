package cn.colg.clz;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.hutool.core.lang.Console;

/**
 * 季节枚举类 - 自定义实现 测试
 *
 * @author colg
 */
public class SeasonTest extends BaseTest {

    /**
     * Test method for {@link cn.colg.clz.Season#toString()}.
     */
    @Test
    public void testToString() {
        Console.log("cn.colg.clz.SeasonTest.testToString()");
        Season spring = Season.SPRING;
        Console.log(spring);
        String seasonName = spring.getSeasonName();
        Console.log(seasonName);
    }

}
