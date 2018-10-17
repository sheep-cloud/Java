package cn.colg.enums;

import java.lang.Thread.State;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.hutool.core.lang.Console;

/**
 * 季节枚举类 测试
 *
 * @author colg
 */
public class SeasonEnumTest extends BaseTest {

    /**
     * Test method for {@link cn.colg.enums.SeasonEnum#toString()}.
     */
    @Test
    public void testToString() {
        // 1. values();
        SeasonEnum[] seasonEnums = SeasonEnum.values();
        Console.log(seasonEnums);
        Console.log("----------------------------------------------------------------------------------------------------");

        // 2. valueOf(String str); 要求传入的形参name是枚举类对象的名字
        String str = "SPRING";
        SeasonEnum seasonEnum = SeasonEnum.valueOf(str);
        seasonEnum.show();
        Console.log(seasonEnum);
        Console.log("----------------------------------------------------------------------------------------------------");

        // 3. values();
        State[] states = Thread.State.values();
        Console.log(states);
    }

    /**
     * Test method for {@link cn.colg.enums.SeasonEnum#show()}.
     */
    @Test
    public void testShow() throws Exception {
        SeasonEnum.SPRING.show();
        SeasonEnum.SUMMER.show();
        SeasonEnum.FALL.show();
        SeasonEnum.WINTER.show();
    }

}
