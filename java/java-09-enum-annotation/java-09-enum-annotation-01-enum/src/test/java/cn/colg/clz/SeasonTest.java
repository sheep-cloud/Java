package cn.colg.clz;

import org.junit.Test;

import cn.hutool.core.lang.Console;

/**
 * 季节枚举类 - 自定义实现 测试
 *
 * @author colg
 */
public class SeasonTest {

    /**
     * Test method for {@link cn.colg.core.BaseEntity#toString()}.
     */
    @Test
    public void testToString() {
        Season spring = Season.SPRING;
        Console.log(spring);

        Console.log(spring.getSeasonName() + ": " + spring.getSeasonDesc());
    }

}
