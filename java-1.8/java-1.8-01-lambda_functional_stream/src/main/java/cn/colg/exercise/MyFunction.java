package cn.colg.exercise;

/**
 * 函数式接口
 *
 * @author colg
 */
@FunctionalInterface
public interface MyFunction {

    /**
     * 获取处理后的字符串
     *
     * @param str
     * @return
     */
    String getValue(String str);
}
