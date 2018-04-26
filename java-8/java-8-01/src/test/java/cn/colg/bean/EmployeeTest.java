package cn.colg.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cn.colg.strategy.FilterEmployeeByAge;
import cn.colg.strategy.FilterEmployeeBySalary;
import cn.colg.strategy.MyStrategy;
import cn.hutool.core.lang.Console;

/**
 * 员工过滤测试
 *
 * @author colg
 */
public class EmployeeTest {
    
    /** 初始化员工信息 */
    private List<Employee> employees = Arrays.asList(
            new Employee("Jack", 18, 9999.99),
            new Employee("Rose", 38, 5555.99),
            new Employee("Tom", 50, 6666.99),
            new Employee("Jax", 16, 3333.99),
            new Employee("Luo", 8, 7777.99)
        );
    
    /*
     * 需求：
     *  1、获取当前公司中员工年龄大于35的员工信息
     *  2、获取当前公司员工工资大于5000的员工信息
     */
    
    /// ----------------------------------------------------------------------------------------------------
    
    /**
     * 1、获取当前公司中员工年龄大于35的员工信息
     *
     * @param list
     * @return
     */
    private List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() > 35) {
                emps.add(employee);
            }
        }
        return emps;
    }
    
    /**
     * 2、获取当前当前公司员工工资大于5000的员工信息
     *
     * @param list
     * @return
     */
    private List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getSalary() > 5000) {
                emps.add(employee);
            }
        }
        return emps;
    }
    
    @Test
    public void test01() throws Exception {
        Console.log("cn.colg.bean.EmployeeTest.test01()");
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list) {
            Console.log(employee);
        }
    }
    
    @Test
    public void test02() throws Exception {
        Console.log("cn.colg.bean.EmployeeTest.test02()");
        List<Employee> list = filterEmployees2(employees);
        for (Employee employee : list) {
            Console.log(employee);
        }
    }
    
    /// ----------------------------------------------------------------------------------------------------

    /**
     * 优化方式一：策略模式
     *
     * @param list
     * @param mp
     * @return
     */
    private List<Employee> filterEmployee(List<Employee> list, MyStrategy<Employee> mp) {
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (mp.compartor(employee)) {
                emps.add(employee);
            }
        }
        return emps;
    }
    
    @Test
    public void test03() throws Exception {
        Console.log("cn.colg.bean.EmployeeTest.test03()");
        List<Employee> list = filterEmployee(employees, new FilterEmployeeByAge());
        for (Employee employee : list) {
            Console.log(employee);
        }
        
        Console.log("----------------------------------------------------------------------------------------------------");
        
        List<Employee> list2 = filterEmployee(employees, new FilterEmployeeBySalary());
        for (Employee employee : list2) {
            Console.log(employee);
        }
    }
    
    /**
     * 优化方式二：策略模式 + 匿名内部类
     *
     * @throws Exception
     */
    @Test
    public void test04() throws Exception {
        Console.log("cn.colg.bean.EmployeeTest.test04()");
        List<Employee> list = filterEmployee(employees, new MyStrategy<Employee>() {

            @Override
            public boolean compartor(Employee t) {
                return t.getSalary() < 5000;
            }
        });
        
        for (Employee employee : list) {
            Console.log(employee);
        }
    }
    
    /**
     * 优化方式三：策略模式 + Lambda表达式
     *
     * @throws Exception
     */
    @Test
    public void test05() throws Exception {
        Console.log("cn.colg.bean.EmployeeTest.test05()");
        List<Employee> list = filterEmployee(employees, (employee) -> employee.getSalary() < 5000);
        list.forEach(System.out::println);
    }
    
    /**
     * 优化方式四：Stream API + Lambda表达式
     *
     * @throws Exception
     */
    @Test
    public void test06() throws Exception {
        Console.log("cn.colg.bean.EmployeeTest.test06()");
        employees.stream()
            .filter((employee) -> employee.getSalary() > 5000)
            .limit(2)
            .forEach(System.out::println);
            
        Console.log("----------------------------------------------------------------------------------------------------");
        
        employees.stream()
            .map(Employee::getName)
            .forEach(System.out::println);
    }
}
