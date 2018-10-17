package cn.colg.functional;

/**
 * 函数式接口
 *
 * @author colg
 */
@FunctionalInterface
public interface MyFun<T> {

    /**
     * 根据传入的num执行运算
     *
     * @param t 数字
     * @return
     * @author colg
     */
    Integer getValue(Integer t);
}
