package cn.colg.functional;

import java.util.List;

import org.junit.Test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

/**
 * java8 内置的四大核心函数式接口 测试
 * 
 * @author colg
 */
public class FunctionalTest {

    @Test
    public void testConsumer() throws Exception {
        Console.log("cn.colg.functional.FunctionalTest.testConsumer()");
        double money = 10000;
        Functional.happy(money, m -> Console.log("消费了" + m + "元"));
    }

    @Test
    public void testSupplier() throws Exception {
        Console.log("cn.colg.functional.FunctionalTest.testSupplier()");
        int length = 10;
        List<Integer> list = Functional.getNumList(length, () -> RandomUtil.randomInt(0, 100));
        Console.log(list);
    }

    @Test
    public void testFunction() throws Exception {
        Console.log("cn.colg.functional.FunctionalTest.testFunction()");
        String value = "colg-cloud!";
        String result = Functional.strHandler(value, str -> StrUtil.upperFirst(str));
        Console.log(result);
    }

    @Test
    public void testPredicate() throws Exception {
        Console.log("cn.colg.functional.FunctionalTest.testPredicate()");
        List<String> list = CollUtil.newArrayList("colg", "cloud", "github", "jdk1.8");
        List<String> result = Functional.filterStr(list, str -> str.contains("c"));
        Console.log(result);
    }

}
