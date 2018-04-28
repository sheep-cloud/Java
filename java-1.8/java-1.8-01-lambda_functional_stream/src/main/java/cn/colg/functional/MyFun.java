package cn.colg.functional;

/**
 * 函数式接口
 *
 * @author colg
 */
public interface MyFun<T> {

    /**
     * 根据num执行运算
     *
     * @param num
     * @return
     */
    Integer getValue(Integer num);
}
