package cn.colg.stream;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;

/**
 * Stream API 2.中间操作 测试
 *
 * @author colg
 */
@Slf4j
public class StreamApi02Test extends BaseTest{
    
    /** 初始化员工信息 */
    private List<Employee> employees = CollUtil.newArrayList(
            new Employee("Jack", 18, 2222.99),
            new Employee("Rose", 28, 5555.99),
            new Employee("Tom", 50, 4444.99),
            new Employee("Jax", 16, 3333.99),
            new Employee("Luo", 40, 7777.99),
            new Employee("Luo", 8, 7777.99),
            new Employee("Luo", 8, 7777.99)
        );
    
    /*
     * 筛选与切片：
     *  filter：       接收Lambda，从流中排除某些元素。
     *  limit：          截断流，使其元素不超过给定数量。  
     *  skip(n)：    跳过元素，返回一个扔掉前n个元素的流。若流中元素不是n个，则返回一个空流。与limit(n)互补
     *  distinct： 筛选，通过流所生成元素的hashCode()和equals去除重复元素
     *  
     * 映射：
     *  map：                接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *  flatMap：    接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     *  
     * 排序：
     *  sorted()：   自然排序
     *  sorted(Comparator comparator)：  定制排序
     */
    
    /// ----------------------------------------------------------------------------------------------------

    
    /**
     * filter： 接收Lambda，从流中排除某些元素。
     *
     * @throws Exception
     */
    @Test
    public void testFilter() throws Exception {
        Console.log("cn.colg.StreamApi02Test.testFilter()");
        // 创建 stream
        Stream<Employee> stream = employees.stream();
        
        // 中间操作：不会执行任何操作
        stream = stream.filter(e -> {
            Console.log("Stream API的中间操作");
            return e.getAge() > 35;
        });
        
        // 终止操作：一次性执行全部内容，即"惰性求职"
        // 内部迭代：迭代操作由Stream API完成
        stream.forEach(Console::log);
    }
    
    /**
     * limit：    截断流，使其元素不超过给定数量。
     *
     * @throws Exception
     */
    @Test
    public void testLimit() throws Exception {
        employees.stream()
                 .limit(2)
                 .forEach(Console::log);
    }
    
    /**
     * skip(n)：    跳过元素，返回一个扔掉前n个元素的流。若流中元素不是n个，则返回一个空流。与limit(n)互补
     *
     * @throws Exception
     */
    @Test
    public void testSkip() throws Exception {
        employees.stream()
                 .skip(2L)
                 .forEach(Console::log);
    }
    
    /**
     * SkipLimit：集合分页，skip((页码-1) * 每页显示的条数).limit(每页显示的条数)
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void testSkipLimit() throws Exception {
        employees.stream()
                 .skip(2)
                 .limit(2)
                 .forEach(Console::log);
    }
    
    /**
     * distinct： 筛选，通过流所生成元素的hashCode()和equals去除重复元素
     *
     * @throws Exception
     */
    @Test
    public void testDistinct() throws Exception {
        employees.stream()
                 .distinct()
                 .forEach(Console::log);
    }
    
    /**
     * map：                接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *
     * @throws Exception
     */
    @Test
    public void testMap() throws Exception {
        List<String> list = CollUtil.newArrayList("colg", "cloud", "java");
        list.stream()
            .map(str -> str.toUpperCase())
            .forEach(Console::log);
        log.info("------------------------------------------------------------------------------------------");
        
        employees.stream()
                 .map(Employee::getName)
                 .forEach(Console::log);
    }
    
    /**
     * sorted()：   自然排序</br>
     * sorted(Comparator comparator)：  定制排序
     *
     * @throws Exception
     */
    @Test
    public void testSorted() throws Exception {
        List<String> list = CollUtil.newArrayList("colg", "cloud", "java");
        list.stream()
            .sorted()
            .forEach(Console::log);
        
        employees.stream()
                 .sorted((e1, e2) -> {
                     if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                 }).forEach(Console::log);
    }
}
