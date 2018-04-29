package cn.colg;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import cn.colg.entity.Employee;
import cn.colg.enums.Status;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;

/**
 * Stream API 3.终止操作（终端操作） 测试
 *
 * @author colg
 */
public class StreamApi03Test {

    /** 初始化员工信息 */
    private List<Employee> employees = CollUtil.newArrayList(
            new Employee("Jack", 18, 2222.99, Status.FREE),
            new Employee("Rose", 28, 5555.99, Status.BUSY),
            new Employee("Tom", 50, 4444.99, Status.VOCATION),
            new Employee("Jax", 16, 3333.99, Status.FREE),
            new Employee("Luo", 8, 7777.99, Status.BUSY)
        );
    
    /*
     * 查找与匹配：
     *  allMatch：       检查是否匹配所有元素
     *  anyMatch：       检查是否至少匹配一个元素
     *  noneMatch：    检查是否没有匹配所有元素
     *  findFirst：    返回第一个元素
     *  findAny：          返回当前流中的任意元素
     *  count：                返回流中元素的总个数
     *  max：                      返回流中最大值
     *  min：                      返回流中最小只
     */
    
    /// ----------------------------------------------------------------------------------------------------

    @Test
    public void testMatchFind() throws Exception {
        Console.log("cn.colg.StreamApi03Test.testAllMatch()");
        boolean allMatch = employees.stream()
                                    .allMatch(e -> e.getStatus().equals(Status.BUSY));
        Console.log(allMatch);
        
        Console.log("----------------------------------------------------------------------------------------------------");
        
        boolean anyMatch = employees.stream()
                                    .anyMatch(e -> e.getStatus().equals(Status.BUSY));
        Console.log(anyMatch);
        
        Console.log("----------------------------------------------------------------------------------------------------");
        
        boolean noneMatch = employees.stream()
                                     .noneMatch(e -> e.getStatus().equals(Status.BUSY));
        Console.log(noneMatch);
        
        Console.log("----------------------------------------------------------------------------------------------------");
        
        Optional<Employee> findFirst = employees.stream()
                                                .findFirst();
        Console.log(findFirst.orElse(new Employee()));
        
        Console.log("----------------------------------------------------------------------------------------------------");
        
        Optional<Employee> findAny = employees.parallelStream()
                                              .filter(e -> e.getStatus().equals(Status.FREE))
                                              .findAny();
        Console.log(findAny.orElse(new Employee()));
    }
    
}
