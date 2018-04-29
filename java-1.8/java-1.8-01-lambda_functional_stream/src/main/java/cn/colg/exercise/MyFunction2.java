package cn.colg.exercise;

/**
 * 函数式接口
 *
 * @author colg
 */
@FunctionalInterface
public interface MyFunction2<T, R> {

    /**
     * 获取计算结果
     *
     * @param t1
     * @param t2
     * @return
     */
    R getValue(T t1, T t2);
}
