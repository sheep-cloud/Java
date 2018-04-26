package cn.colg.strategy;

/**
 * 过滤对象信息的接口 </br>
 * 
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。</br>
 * 
 * '@FunctionalInterface'： 检查是否是函数式接口
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
     */
    boolean compartor(T t);
}
