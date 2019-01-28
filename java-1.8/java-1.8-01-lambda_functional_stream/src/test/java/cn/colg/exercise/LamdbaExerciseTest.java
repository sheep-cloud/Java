package cn.colg.exercise;

import java.util.List;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Lambda 表达式练习 
 *
 * @author colg
 */
@Slf4j
public class LamdbaExerciseTest extends BaseTest{
    
    /** 初始化员工信息 */
    private List<Employee> employees = CollUtil.newArrayList(
            new Employee("Jack", 18, 2222.99),
            new Employee("Rose", 28, 5555.99),
            new Employee("Tom", 50, 4444.99),
            new Employee("Jax", 16, 3333.99),
            new Employee("Luo", 8, 7777.99)
        );

    /**
     * 调用CollUtil.sort()方法, 通过定制排序比较两个Employee(先按年龄比, 年龄相同按姓名比), 使用Lambda作为参数传递.
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test01() throws Exception {
        CollUtil.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                // 年龄相同, 比较姓名(升序)
                return e1.getName().compareTo(e2.getName()); 
            } else {
                // 年龄不同, 比较年龄(升序)
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        log.info("employees : {}", employees);
    }
    
    /**
     * 函数式接口
     * 
     * <pre>
     *  1. 声明函数式接口, 接口中声明抽象方法, public String getValue(String str);
     *  2. 声明类TestLambda, 类中编写方法, 使用接口作为参数, 将一个字符串转换成大写, 并作为方法的返回值.
     *  3. 再将一个字符串的第2个和第4个索引位置进行截取子串.
     * </pre>
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test02() throws Exception {
        String value = "colg-cloud!";
        String result = TestLambda.strHandler(value, str -> StrUtil.upperFirst(str));
        log.info("result {}", result);
        
        result = TestLambda.strHandler(value, str -> StrUtil.sub(str, 2, 4));
        log.info("result {}", result);
    }
    
    /**
     * 函数式接口
     * <pre>
     *  1. 声明一个带两个泛型的函数式接口, 泛型类型为<T, R>, T为参数, R为返回值.
     *  2. 接口中声明对应抽象方法.
     *  3. 在TestLambda类中声明方法, 使用接口作为参数, 计算两个long型参数的和
     *  4. 再计算两个long型参数的乘积.
     * </pre>
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test03() throws Exception {
        long t1 = 100L;
        long t2 = 200L;
        long result = TestLambda.getValue(t1, t2, (x, y) -> x + y);
        log.info("result {}", result);
        
        result = TestLambda.getValue(t1, t2, (x, y) -> x * y);
        log.info("result {}", result);
    }
}
