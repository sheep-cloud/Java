package cn.colg.exercise;

import java.util.List;

import org.junit.Test;

import cn.colg.entity.Employee;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;

/**
 * Lambda 表达式练习 
 *
 * @author colg
 */
public class LamdbaExerciseTest {
    
    /** 初始化员工信息 */
    private List<Employee> employees = CollUtil.newArrayList(
            new Employee("Jack", 18, 2222.99),
            new Employee("Rose", 28, 5555.99),
            new Employee("Tom", 50, 4444.99),
            new Employee("Jax", 16, 3333.99),
            new Employee("Luo", 8, 7777.99)
        );

    /**
     * 调用CollUtil.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按姓名比），使用Lambda作为参数传递。
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        Console.log("cn.colg.exercise.LamdbaExerciseTest.test01()");
        CollUtil.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                // 年龄相等，比较姓名（升序）
                return e1.getName().compareTo(e2.getName()); 
            } else {
                // 年龄不相等，比较年龄（升序）
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        
        Console.log(employees);
    }
    
    /**
     * 1. 声明函数式接口，接口中声明抽象方法，public Stirng getValue(String str);</br>
     * 2. 声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。</br>
     * 3. 再将一个字符串的第2个和第4个索引位置进行截取子串。
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        Console.log("cn.colg.exercise.LamdbaExerciseTest.test02()");
        String value = "colg-cloud!";
        String result = TestLambda.strHandler(value, str -> StrUtil.upperFirst(str));
        Console.log(result);
        
        String result2 = TestLambda.strHandler(value, str -> StrUtil.sub(str, 2, 5));
        Console.log(result2);
    }
    
    /**
     * 1. 声明一个带两个泛型的函数式接口，反省类型为<T, R>，T为参数，R为返回值.</br>
     * 2. 接口中声明对应抽象方法。</br>
     * 3. 在TetLambda类中声明方法，使用接口作为参数，计算两个long型参数的和。</br>
     * 4. 再计算两个long型参数的乘积。
     *
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        Console.log("cn.colg.exercise.LamdbaExerciseTest.test03()");
        long t1 = 100L;
        long t2 = 200L;
        long result = TestLambda.getValue(t1, t2, (x, y) -> x + y);
        Console.log(result);
        
        long result2 = TestLambda.getValue(t1, t2, (x, y) -> x * y);
        Console.log(result2);
    }
}
