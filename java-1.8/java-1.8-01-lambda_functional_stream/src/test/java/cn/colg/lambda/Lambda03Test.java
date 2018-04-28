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

    /*
     * 需求：
     *  1、对一个数进行运算
     */

    /// ----------------------------------------------------------------------------------------------------

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
        MyFun<Integer> mf = new MyFun<Integer>() {

            @Override
            public Integer getValue(Integer num) {
                return num * 10;
            }
        };
        Console.log(operation(10, mf));
        
        // 参数列表：(x)
        // 方法体：x * x
        Integer result = operation(100, (x) -> x * x);
        Console.log(result);
        
        Console.log(operation(200, (x) -> x + 200));
    }
}
