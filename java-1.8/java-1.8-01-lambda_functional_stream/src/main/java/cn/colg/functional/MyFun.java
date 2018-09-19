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
     * @param num
     * @return
     */
    Integer getValue(Integer t);
}
