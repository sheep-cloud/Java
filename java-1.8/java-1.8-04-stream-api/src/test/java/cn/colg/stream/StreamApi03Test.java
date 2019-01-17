package cn.colg.stream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import cn.colg.enums.Status;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Stream API 3.终止操作（终端操作） 测试
 *
 * @author colg
 */
@Slf4j
public class StreamApi03Test extends BaseTest{

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
     *  min：                      返回流中最小值
     *  collect：          收集，将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    
    /// ----------------------------------------------------------------------------------------------------

    @Test
    public void testMatchFind() throws Exception {
        Set<Employee> set = employees.stream()
                                     .collect(Collectors.toSet());
        log.info("set: {}", set);
        log.info("------------------------------------------------------------------------------------------");
        
        boolean allMatch = employees.stream()
                                    .allMatch(e -> e.getStatus().equals(Status.BUSY));
        log.info("allMatch: {}", allMatch);
        log.info("------------------------------------------------------------------------------------------");
        
        boolean anyMatch = employees.stream()
                                    .anyMatch(e -> e.getStatus().equals(Status.BUSY));
        log.info("anyMatch: {}", anyMatch);
        
        log.info("------------------------------------------------------------------------------------------");
        
        boolean noneMatch = employees.stream()
                                     .noneMatch(e -> e.getStatus().equals(Status.BUSY));
        log.info("noneMatch: {}", noneMatch);
        log.info("------------------------------------------------------------------------------------------");
        
        Employee employee = employees.stream()
                                   .findFirst()
                                   .orElse(new Employee());
        log.info("employee: {}", employee);
        log.info("------------------------------------------------------------------------------------------");
        
        employee = CollUtil.getFirst(employees);
        log.info("employee: {}", employee);
        log.info("------------------------------------------------------------------------------------------");
        
        employee = employees.stream()
                             .filter(e -> e.getStatus().equals(Status.FREE))
                             .findAny()
                             .orElse(new Employee());
        log.info("employee: {}", employee);
    }
}
