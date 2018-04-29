package cn.colg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import cn.colg.entity.Employee;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;

/**
 * Stream API 1.创建Stream 测试
 * 
 * <pre>
 * 一、Stream 的三个操作步骤：
 *  1. 创建Stream
 *  
 *  2. 中间操作
 *  
 *  3. 终止操作（终端操作）
 * </pre>
 *
 * @author colg
 */
public class StreamApi01Test {

    @SuppressWarnings("unused")
    @Test
    public void test01() throws Exception {
        Console.log("cn.colg.StreamApiTest.test01()");
        // 1. 可以通过Collection系列集合提供的stream()或parallelStream()
        List<Employee> list = new ArrayList<>();
        Stream<Employee> stream = list.stream();

        // 2. 通过Arrays中的静态方法stream(...)获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        // 3. 通过Stream的静态方法of()
        Stream<String> stream3 = Stream.of("colg", "cloud", "java");

        // 4. 创建无限流
        //  1) 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 1);
        stream4.limit(10)
               .forEach(Console::log);
        
        // 4. 创建无限流
        //  2) 生成
        Stream<Integer> stream5 = Stream.generate(() -> RandomUtil.randomInt());
        stream5.limit(10)
               .forEach(Console::log);
    }
    
    @Test
    public void test02() throws Exception {
        Console.log("cn.colg.StreamApiTest.test02()");
        List<String> list = CollUtil.newArrayList("Jack", "Rose", "Tom", null);
        list.stream()
            .filter(str -> (str != null && str.contains("o")))
            .forEach(Console::log);
        ;
    }
}
