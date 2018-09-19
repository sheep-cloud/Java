package cn.colg;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

import cn.colg.entity.Employee;
import cn.hutool.core.lang.Console;

/**
 * 方法引用：若Lambda体的内容有方法已经实现了，我们可以使用"方法引用"（可以理解为方法引用是Lambda表达式的另外一种表现形式）
 * 
 * <pre>
 * 主要有三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 * </pre>
 *
 * @author colg
 */
public class MethodRefTest {

    /**
     * 方法引用
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        Console.log("cn.colg.MethodRefTest.test01()");
        String value = "colg";

        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept(value);
        Consumer<String> consumer2 = x -> Console.log(x);
        consumer2.accept(value);

        // 类::实例方法名
        Consumer<String> consumer3 = System.out::println;
        consumer3.accept(value);
        // 类::静态方法名
        Consumer<String> consumer4 = Console::log;
        consumer4.accept(value);
    }

    /**
     * 对象::实例方法名
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        Console.log("cn.colg.MethodRefTest.test02()");
        Employee employee = new Employee("colg", 28, 9999.99);

        Supplier<String> supplier = () -> employee.getName();
        Console.log(supplier.get());

        // 方法引用
        Supplier<String> supplier2 = employee::getName;
        Console.log(supplier2.get());
    }
}
