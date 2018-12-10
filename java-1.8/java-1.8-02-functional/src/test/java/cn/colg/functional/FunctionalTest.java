package cn.colg.functional;

import java.util.List;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * java8 内置的四大核心函数式接口 测试
 * 
 * @author colg
 */
@Slf4j
public class FunctionalTest extends BaseTest{

    @Test
    public void testConsumer() throws Exception {
        double money = 60000.0;
        Functional.happy(money, m -> log.info("消费了 {} 元", m));
    }

    @Test
    public void testSupplier() throws Exception {
        int length = 10;
        List<Integer> list = Functional.getNumList(length, () -> RandomUtil.randomInt(0, 100));
        log.info("list : {}", list);
    }

    @Test
    public void testFunction() throws Exception {
        String value = "colg-cloud!";
        String result = Functional.strHandler(value, str -> StrUtil.upperFirst(str));
        log.info("result : {}", result);
    }

    @Test
    public void testPredicate() throws Exception {
        List<String> list = CollUtil.newArrayList("colg", "cloud", "github", "jdk1.8");
        List<String> result = Functional.filterStr(list, str -> str.contains("c"));
        log.info("result : {}", result);
    }
}
