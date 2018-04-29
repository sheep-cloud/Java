package cn.colg.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置的四大核心函数式接口
 * 
 * <pre>
 * Consumer<T>：消费型接口
 *  void accept(T t);
 * 
 * Supplier<T>：供给型接口
 *  T get();
 *  
 * Function<T, R>：函数型接口
 *  R apply(T t);
 *  
 * Predicate<T>：段言型接口
 *  boolean test(T t);
 * </pre>
 *
 * @author colg
 */
public class Functional {

    /**
     * 消费型接口 - 消费金额
     *
     * @param money
     * @param consumer
     */
    public static void happy(double money, Consumer<Double> consumer) {
        // 对给定的参数执行此操作。
        consumer.accept(money);
    }

    /**
     * 供给型接口 - 获取指定数量的数字集合
     *
     * @param num
     * @param supplier
     * @return
     */
    public static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        // 生成指定数量的数字
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    };

    /**
     * 函数式接口 - 对字符串进行处理
     *
     * @param str
     * @param function
     * @return
     */
    public static String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * 段言型接口 - 对集合中的字符串进行过滤
     *
     * @param list
     * @param predicate
     * @return
     */
    public static List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (predicate.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }
}
