package cn.colg.exercise;

/**
 * 函数式接口，功能类
 *
 * @author colg
 */
public class TestLambda {

    /**
     * 通过函数式接口处理字符串
     *
     * @param str
     * @param myFunction
     * @return
     */
    public static String strHandler(String str, MyFunction myFunction) {
        return myFunction.getValue(str);
    }

    /**
     * 通过函数式接口计算结果
     *
     * @param t1
     * @param t2
     * @param myFunction2
     * @return
     */
    public static long getValue(long t1, long t2, MyFunction2<Long, Long> myFunction2) {
        return myFunction2.getValue(t1, t2);
    }
}
