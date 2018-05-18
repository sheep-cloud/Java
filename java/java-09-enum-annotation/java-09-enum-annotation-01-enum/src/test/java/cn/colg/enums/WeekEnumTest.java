package cn.colg.enums;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.hutool.core.lang.Console;

/**
 * 星期枚举 测试
 *
 * @author colg
 */
public class WeekEnumTest extends BaseTest{

    /**
     * Test method for {@link cn.colg.enums.WeekEnum#getChinese()}.
     */
    @Test
    public void testGetChinese() {
        Console.log("cn.colg.enums.WeekEnumTest.testGetChinese()");
        WeekEnum[] weekEnums = WeekEnum.values();
        Console.log(weekEnums);
    }

}
