package cn.colg;

import java.util.function.Supplier;

import org.junit.Test;

import cn.colg.entity.Employee;
import cn.hutool.core.lang.Console;

/**
 * 构造器引用：与函数式接口相结合，自动与函数式接口中方法兼容。可以把构造器引用赋值给定义的方法，与构造器参数列表要与接口中抽象方法的参数列表一致！
 * 
 * <pre>
 * 语法格式：
 *  ClassName::new
 * </pre>
 *
 * @author colg
 */
public class ConstructorRefTest {

    @Test
    public void test01() throws Exception {
        Console.log("cn.colg.ConstructorRefTest.test01()");
        Supplier<Employee> supplier = () -> new Employee();
        Console.log(supplier.get());

        // 构造器引用
        Supplier<Employee> supplier2 = Employee::new;
        Console.log(supplier2.get());
    }
}
