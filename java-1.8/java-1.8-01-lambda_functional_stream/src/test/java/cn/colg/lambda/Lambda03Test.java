package cn.colg.lambda;

import org.junit.Test;

import cn.colg.functional.MyFun;
import cn.hutool.core.lang.Console;

/**
 * Lambda 表达式测试
 *
 * @author colg
 */
public class Lambda03Test {

    /**
     * 对一个数进行运算
     *
     * @param num
     * @param mf
     * @return
     */
    private <T> Integer operation(Integer num, MyFun<T> mf) {
        return mf.getValue(num);
    }

    @Test
    public void test01() throws Exception {
        Console.log("cn.colg.lambda.Lambda03Test.test01()");
        Console.log(operation(10, new MyFun<Integer>() {
            
            @Override
            public Integer getValue(Integer num) {
                return num * 10;
            }
        }));
        
        // 参数列表：(x)
        // 方法体：x * x
        Console.log(operation(10, (x) -> x * 10));

        Console.log(operation(200, x -> x + 200));
    }
}
