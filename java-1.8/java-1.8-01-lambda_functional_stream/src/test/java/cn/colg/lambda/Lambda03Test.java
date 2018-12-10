package cn.colg.lambda;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.functional.MyFun;
import lombok.extern.slf4j.Slf4j;

/**
 * Lambda 表达式测试
 *
 * @author colg
 */
@Slf4j
public class Lambda03Test extends BaseTest{

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
        Integer result = operation(10, new MyFun<Integer>() {
            
            @Override
            public Integer getValue(Integer num) {
                return num * 10;
            }
        });
        log.info("result : {}", result);
        
        // 参数列表：(x)
        // 方法体：x * x
        result = operation(10, (x) -> x * 10);
        log.info("result : {}", result);

        result = operation(200, x -> x + 200);
        log.info("result : {}", result);
    }
}
