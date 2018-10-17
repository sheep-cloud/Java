package cn.colg.enums;

import java.lang.Thread.State;

import org.junit.Test;

import cn.hutool.core.lang.Console;

/**
 * 季节枚举类lombok 测试
 *
 * @author colg
 */
public class SeasonEnumLombokTest {
    
    /**
     * Test method for {@link cn.colg.enums.SeasonEnumLombok#toString()}.
     */
    @Test
    public void testToString() {
        // 1. values();
        SeasonEnumLombok[] seasonEnumLomboks = SeasonEnumLombok.values();
        Console.log(seasonEnumLomboks);
        Console.log("----------------------------------------------------------------------------------------------------");

        // 2. valueOf(String str); 要求传入的形参name是枚举类对象的名字
        String str = "SPRING";
        SeasonEnumLombok seasonEnumLombok = SeasonEnumLombok.valueOf(str);
        seasonEnumLombok.show();
        Console.log(seasonEnumLombok);
        Console.log("----------------------------------------------------------------------------------------------------");

        // 3. values();
        State[] states = Thread.State.values();
        Console.log(states);
    }

    /**
     * Test method for {@link cn.colg.enums.SeasonEnumLombok#show()}.
     */
    @Test
    public void testShow() {
        SeasonEnumLombok.SPRING.show();
        SeasonEnumLombok.SUMMER.show();
        SeasonEnumLombok.FALL.show();
        SeasonEnumLombok.WINTER.show();
    }

}
