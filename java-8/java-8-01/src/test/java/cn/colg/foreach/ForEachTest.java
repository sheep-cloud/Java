package cn.colg.foreach;

import java.util.List;

import org.junit.Test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;

/**
 * Java1.8 ForEach 测试
 *
 * @author colg
 */
public final class ForEachTest {

    /**
     * ForEach 遍历集合
     *
     * @throws Exception
     */
    @Test
    public void testForEachCollection() throws Exception {
        List<String> list = CollUtil.newArrayList("Jack", "Rose", "Tom", "Jax");

        // 1. 推荐
        list.forEach(str -> Console.log(str));

        // 2. 不推荐
        list.forEach(System.out::println);

        // 3. 过滤
        list.stream().filter(str -> !"Jack".equals(str)).forEach(str -> Console.log(str));
    }

    /**
     * 遍历Map，Dict（字典对象，扩充了HashMap中的方法）
     *
     * @throws Exception
     */
    @Test
    public void testDict() throws Exception {
        Dict dict = new Dict(4);
        dict.set("Jack", 18).set("Rose", 20).set("Tom", 22).set("Jax", 25);

        dict.forEach((key, value) -> {
            Console.log(key + ": " + value);
        });
    }

}
