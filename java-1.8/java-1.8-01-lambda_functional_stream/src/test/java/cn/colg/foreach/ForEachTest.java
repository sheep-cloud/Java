package cn.colg.foreach;

import java.util.List;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;

/**
 * Java1.8 ForEach 测试
 *
 * @author colg
 */
public final class ForEachTest extends BaseTest{
    
    @Test
    public void testArray() throws Exception {
        Console.log("cn.colg.foreach.ForEachTest.testArray()");
        // 新建一个Array
        String[] strings = {"Jack", "Rose", "Tom", "Jax"};
        
        CollUtil.newArrayList(strings).forEach(Console::log);
    }

    /**
     * ForEach 遍历集合
     *
     * @throws Exception
     */
    @Test
    public void testForEachCollection() throws Exception {
        Console.log("cn.colg.foreach.ForEachTest.testForEachCollection()");
        // 新建一个ArrayList
        List<String> list = CollUtil.newArrayList("Jack", "Rose", "Tom", "Jax");

        // 1. 推荐
        list.forEach(Console::log);

        // 2. 过滤
        list.stream().filter(str -> !"Jack".equals(str))
                     .forEach(Console::log);
    }

    /**
     * 遍历Map，Dict（字典对象，扩充了HashMap中的方法）
     *
     * @throws Exception
     */
    @Test
    public void testDict() throws Exception {
        Console.log("cn.colg.foreach.ForEachTest.testDict()");
        /*
         * 构造时必须指定初始容量：
         *  负载因子：static final float DEFAULT_LOAD_FACTOR = 0.75f;
         *  存储个数：(存储的元素个数 / 负载因子) + 1
         */
        Dict dict = new Dict(6).set("Jack", 18)
                               .set("Rose", 20)
                               .set("Tom", 22)
                               .set("Jax", 25);

        dict.forEach((key, value) -> Console.log(key + ": " + value));
    }
    
}
