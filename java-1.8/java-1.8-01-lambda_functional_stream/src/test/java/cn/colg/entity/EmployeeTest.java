package cn.colg.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.strategy.FilterEmployeeByAge;
import cn.colg.strategy.FilterEmployeeBySalary;
import cn.colg.strategy.MyStrategy;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工Entity 测试
 *
 * @author colg
 */
@Slf4j
public final class EmployeeTest extends BaseTest{
    
    /** 初始化员工信息 */
    private List<Employee> employees = CollUtil.newArrayList(
        new Employee("Jack", 18, 2222.99),
        new Employee("Rose", 28, 5555.99),
        new Employee("Tom", 50, 4444.99),
        new Employee("Jax", 16, 3333.99),
        new Employee("Luo", 8, 7777.99)
    );

    /**
     * 1. 获取年龄大于35的员工信息
     *
     * @param employees
     * @return
     */
    private List<Employee> filterEmployeesByAge(List<Employee> employees) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() > 35) {
                list.add(employee);
            }
        }
        return list;
    }
    
    /**
     * 2. 获取工资高于5000的员工信息
     *
     * @param employees
     * @return
     */
    private List<Employee> filterEmployeesBySalary(List<Employee> employees) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() > 5000) {
                list.add(employee);
            }
        }
        return list;
    }
    
    @Test
    public void test01() throws Exception {
        List<Employee> list = filterEmployeesByAge(employees);
        log.info("list : {}", list);
        
        list = filterEmployeesBySalary(employees);
        log.info("list : {}", list);
    }
    
    /// ----------------------------------------------------------------------------------------------------

    /**
     * 优化方式一：策略模式
     *
     * @param employees
     * @param myStrategy
     * @return
     */
    private List<Employee> filterEmployeesByStrategy(List<Employee> employees, MyStrategy<Employee> myStrategy) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (myStrategy.compartor(employee)) {
                list.add(employee);
            }
        }
        return list;
    }
    
    /**
     * 策略模式，声明类
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        List<Employee> list = filterEmployeesByStrategy(employees, new FilterEmployeeByAge());
        log.info("list : {}", list);
        
        list = filterEmployeesByStrategy(employees, new FilterEmployeeBySalary());
        log.info("list : {}", list);
    }
    
    /**
     * 策略模式，匿名内部类
     *
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        List<Employee> list = filterEmployeesByStrategy(employees, new MyStrategy<Employee>() {

            @Override
            public boolean compartor(Employee t) {
                return t.getAge() > 35;
            }
        });
        log.info("list : {}", list);
        
        list = filterEmployeesByStrategy(employees, new MyStrategy<Employee>() {

            @Override
            public boolean compartor(Employee t) {
                return t.getSalary() > 5000;
            }
        });
        log.info("list : {}", list);
    }
    
    /**
     * 优化方式二：Lambda 表达式
     *
     * @throws Exception
     */
    @Test
    public void test04() throws Exception {
        MyStrategy<Employee> myStrategy = employee -> employee.getAge() > 35;
        List<Employee> list = filterEmployeesByStrategy(employees, myStrategy);
        log.info("list : {}", list);
        
        list = filterEmployeesByStrategy(employees, employee -> employee.getSalary() > 5000);
        log.info("list : {}", list);
    }
    
    /**
     * Stream API 过滤
     *
     * @throws Exception
     */
    @Test
    public void test05() throws Exception {
        List<Employee> list = employees.stream().filter(employee -> employee.getAge() > 35).collect(Collectors.toList());
        log.info("list : {}", list);
        
        list = employees.stream().filter(employee -> employee.getSalary() > 5000).collect(Collectors.toList());
        log.info("list : {}", list);
    }
}
