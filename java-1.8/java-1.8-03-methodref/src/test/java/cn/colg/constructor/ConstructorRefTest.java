package cn.colg.constructor;

import java.util.function.Supplier;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class ConstructorRefTest extends BaseTest{

    @Test
    public void test01() throws Exception {
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();
        log.info("employee : {}", employee);

        // 构造器引用
        supplier = Employee::new;
        employee = supplier.get();
        log.info("employee : {}", employee);
    }
}
