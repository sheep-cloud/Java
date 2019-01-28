package cn.colg.strategy;

/**
 * 过滤对象信息的接口
 * 
 * <pre>
 * 函数式接口:
 *  接口中只有一个抽象方法的接口, 称为函数式接口.
 *  `@FunctionalInterface`: 检查是否是函数式接口
 * </pre>
 *
 * @author colg
 */
@FunctionalInterface
public interface MyStrategy<T> {

    /**
     * 返回比较后的结果
     *
     * @param t
     * @return
     * @author colg
     */
    boolean compartor(T t);
}
